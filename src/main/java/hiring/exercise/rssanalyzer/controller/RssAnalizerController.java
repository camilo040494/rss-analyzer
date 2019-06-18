package hiring.exercise.rssanalyzer.controller;

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
import hiring.exercise.rssanalyzer.controller.response.OkResponse;

@RestController
@RequestMapping({ "/rss-analyzer" })
@CrossOrigin(origins = "*")
public class RssAnalizerController {
  
  @GetMapping(value = "/health")
  public ResponseEntity<String> health() {
   return new ResponseEntity<>("OK", HttpStatus.OK);
 }
  
  @PostMapping("/analyse/new")
  public ResponseEntity<Integer> create(@RequestBody UrlRequest urls) {
    return new ResponseEntity<>(new Integer(2), HttpStatus.OK);
  }
  
  @GetMapping("/frequency")
  public ResponseEntity<Object> create(@RequestParam int id) {
    //return new ResponseEntity<>(EventService.create(Event), HttpStatus.OK);
    return null;
  }
  
}
