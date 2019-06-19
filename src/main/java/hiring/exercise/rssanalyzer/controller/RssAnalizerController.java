package hiring.exercise.rssanalyzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hiring.exercise.rssanalyzer.controller.request.UrlRequest;
import hiring.exercise.rssanalyzer.controller.response.RelatedNewsResponse;
import hiring.exercise.rssanalyzer.model.MatchedRss;
import hiring.exercise.rssanalyzer.service.AnalyzerRssService;

@RestController
@RequestMapping({ "/rss-analyzer" })
@CrossOrigin(origins = "*")
public class RssAnalizerController {
  
  @Autowired
  private AnalyzerRssService analyzerRssService;
  
  @GetMapping(value = "/health")
  public ResponseEntity<String> health() {
   return new ResponseEntity<>("OK", HttpStatus.OK);
 }
  
  @PostMapping("/analyse/new")
  public ResponseEntity<Integer> create(@RequestBody UrlRequest urls) {
    Integer processRssFeeds = analyzerRssService.processRssFeeds(urls);
    return new ResponseEntity<>(processRssFeeds, HttpStatus.OK);
  }
  
  @GetMapping("/frequency")
  public ResponseEntity<List<RelatedNewsResponse>> create(@RequestParam int id) {
    List<RelatedNewsResponse> relatedNewsFeeds = analyzerRssService.getRelatedNewsFeeds(id);
    return new ResponseEntity<>(relatedNewsFeeds, HttpStatus.OK);
  }
  
}
