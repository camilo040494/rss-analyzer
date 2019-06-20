package hiring.exercise.rssanalyzer.controller.request;

import java.util.List;

import hiring.exercise.rssanalyzer.util.validator.ListValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {
  
  @ListValidator(message = "You must specify more than 1 url's")
  private List<String> urls;
  
}
