package hiring.exercise.rssanalyzer.controller.request;

import java.util.List;

import hiring.exercise.rssanalyzer.util.validator.ListValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {
  
  @ListValidator(message = "Invalid url's: must be valid and more than 1")
  private List<String> urls;
  
}
