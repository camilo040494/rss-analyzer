package hiring.exercise.rssanalyzer.service;

import java.util.List;

public interface AnalyzerRssService {
  
  Object processRssFeeds(List<String> urls);
  
}
