package hiring.exercise.rssanalyzer.service;

import java.util.List;

import hiring.exercise.rssanalyzer.controller.request.UrlRequest;
import hiring.exercise.rssanalyzer.controller.response.RelatedNewsResponse;

public interface AnalyzerRssService {
  
  Integer processRssFeeds(UrlRequest urls);
  
  List<RelatedNewsResponse> getRelatedNewsFeeds(int id);
  
}
