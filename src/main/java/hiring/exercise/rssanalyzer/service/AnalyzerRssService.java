package hiring.exercise.rssanalyzer.service;

import hiring.exercise.rssanalyzer.controller.request.UrlRequest;
import hiring.exercise.rssanalyzer.model.MatchedRss;

public interface AnalyzerRssService {
  
  Integer processRssFeeds(UrlRequest urls);
  
  MatchedRss getRelatedNewsFeeds(int id);
  
}
