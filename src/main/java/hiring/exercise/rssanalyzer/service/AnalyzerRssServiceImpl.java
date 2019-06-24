package hiring.exercise.rssanalyzer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.amazonaws.util.CollectionUtils;
import com.amazonaws.util.StringUtils;
import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import hiring.exercise.rssanalyzer.controller.request.UrlRequest;
import hiring.exercise.rssanalyzer.controller.response.RelatedNewsResponse;
import hiring.exercise.rssanalyzer.exception.business.NonExistentIdException;
import hiring.exercise.rssanalyzer.facade.RssFeedFacade;
import hiring.exercise.rssanalyzer.model.MatchedRss;
import hiring.exercise.rssanalyzer.model.RssData;
import hiring.exercise.rssanalyzer.properties.ApplicationPoliciesProperties;
import hiring.exercise.rssanalyzer.repository.MatchedRssRepository;

@Service
public class AnalyzerRssServiceImpl implements AnalyzerRssService{
  
  private static final String EMPTY_CHARACTER = "";
  private static final String ADMIT_ONLY_LETTER_AND_NUMBER_REGEX = "[0-9,.:;()-]";
//  private static final String ADMIT_ONLY_LETTER_AND_NUMBER_REGEX = "[^a-zA-Z0-9]";
  private static final String SPACE = " ";

  @Autowired
  private MatchedRssRepository matchedRssRepository;
  
  @Autowired
  private RssFeedFacade rssFeedFacade;
  
  @Autowired
  private ApplicationPoliciesProperties applicationPoliciesProperties;
  
  @Override
  public Integer processRssFeeds(UrlRequest urls) {
    List<SyndFeed> processedUrls = processUrls(urls);
    return findMatches(processedUrls);
  }
  
  private Integer findMatches(List<SyndFeed> processedUrls) {
    List<Pair<SyndEntry, String>> feeds = Lists.newArrayList(); 
    for (int i = 0; i < processedUrls.size(); i++) {
      SyndFeed syndFeed = processedUrls.get(i);
      List<SyndEntry> entries = syndFeed.getEntries();
      for (SyndEntry syndEntry : entries) {
        Pair<SyndEntry,String> of = Pair.of(syndEntry, syndFeed.getLink());
        feeds.add(of);
      }
    }
    
    MatchedRss match = new MatchedRss();
    List<String> hotWords = new ArrayList<>();
    
    feeds.stream().forEach(feed -> {
      String[] split = feed.getFirst().getTitle().split(SPACE);
      for (String title : split) {
        String cleanString = title.replaceAll(ADMIT_ONLY_LETTER_AND_NUMBER_REGEX, EMPTY_CHARACTER);
        if (!StringUtils.isNullOrEmpty(cleanString) && notInBlackList(cleanString)) {
          hotWords.add(cleanString);
        }
      }
    });
    
    Set<Pair<String, Long>> keySet = countOcurrences(hotWords);
    
//    if (applicationPoliciesProperties.getIntersectionThroughLinks()) {
//      filterByNotFoundInAllLinks(countByWordSorted, feeds);
//    }
    
    Integer maxCounts = new Integer(applicationPoliciesProperties.getMaxNumberResults());
    for (Pair<String, Long> hotTopic : keySet) {
      if (maxCounts == 0) {
        break;
      }
      findLinks(match, hotTopic.getFirst(), feeds);
      maxCounts--;
    }
    match = matchedRssRepository.save(match);
    return match.getId();
  }
  
  private boolean notInBlackList(String cleanString) {
    if (CollectionUtils.isNullOrEmpty(applicationPoliciesProperties.getBlackListWords())) {
      return true;
    }
    return !applicationPoliciesProperties.getBlackListWords().contains(cleanString);
  }

  private void findLinks(MatchedRss match, String hotTopic, List<Pair<SyndEntry,String>> feeds) {
    for (Pair<SyndEntry,String> syndFeed : feeds) {
       if (syndFeed.getFirst().getTitle().contains(hotTopic)) {
         RssData data = new RssData();
         data.setLink(syndFeed.getFirst().getLink());
         data.setHeader(syndFeed.getFirst().getTitle());
         data.setTopic(hotTopic);
         match.addRssData(data);
       }
     }
  }

  private void filterByNotFoundInAllLinks(Map<String, Long> countByWordSorted, List<Pair<SyndEntry,String>> feeds) {
    Set<String> keySet = new HashSet<String>(countByWordSorted.keySet());
    Map<String, List<Pair<SyndEntry, String>>> collect = feeds.stream().collect(Collectors.groupingBy(Pair::getSecond));
    Set<String> feedsByLink = collect.keySet();
    
    for (String word : keySet) {
      for (String link : feedsByLink) {
        boolean removeWord = true;
        List<Pair<SyndEntry, String>> list = collect.get(link);
        for (Pair<SyndEntry,String> pair : list) {
          if (pair.getFirst().getTitle().contains(word)) {
            removeWord = false;
            break;
          }
        }
        if (removeWord) {
          countByWordSorted.remove(word);
          break;
        }
      }
    }
  }

  private Set<Pair<String, Long>> countOcurrences(List<String> hotWords) {
    Map<String, Long> collect = hotWords.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Set<Pair<String, Long>> countByWordSorted = collect.entrySet()
        .stream()
        .map(word -> Pair.of(word.getKey(), word.getValue()))
        .collect(Collectors.toCollection(
            () -> new TreeSet<Pair<String, Long>>(
                (Pair<String, Long> pair1, Pair<String, Long> pair2) -> 
                  {
                    int number = Long.compare(pair2.getSecond(), pair1.getSecond());
                    if (number == 0) {
                      return pair1.getFirst().compareTo(pair2.getFirst());
                    } else {
                      return number;
                    }
                  } 
                )
            ));
    return countByWordSorted;
  }

  private List<SyndFeed> processUrls(UrlRequest urls) {
    List<String> list = urls.getUrls();
    CopyOnWriteArrayList<SyndFeed> feeds = new CopyOnWriteArrayList<SyndFeed>();
    
    if (!CollectionUtils.isNullOrEmpty(list)) {
      list.parallelStream().forEach(link -> feeds.add(rssFeedFacade.getRssFeed(link)));
      return feeds;
    }
    return Lists.newArrayList();
  }

  @Override
  public List<RelatedNewsResponse> getRelatedNewsFeeds(int id) {
    Optional<MatchedRss> findById = matchedRssRepository.findById(id);
    if (findById.isPresent()) {
      MatchedRss data = findById.get();
      List<RelatedNewsResponse> relatedNews = Lists.newArrayList();
      
      Map<String, List<RssData>> collect = data.getData().stream().collect(Collectors.groupingBy(RssData::getTopic));
      
      for (String topic : collect.keySet()) {
        RelatedNewsResponse response = new RelatedNewsResponse();
        response.setTopic(topic);
        List<RssData> feeds = collect.get(topic);
        response.setFeeds(feeds);
        response.setFrecuency(feeds.size());
        relatedNews.add(response);
      }
      return relatedNews;
    }
    throw new NonExistentIdException(id);
  }
  
}
