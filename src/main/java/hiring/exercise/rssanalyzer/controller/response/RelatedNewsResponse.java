package hiring.exercise.rssanalyzer.controller.response;

import java.util.List;

import hiring.exercise.rssanalyzer.model.RssData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatedNewsResponse {
  
  private String topic;
  private int frecuency;
  private List<RssData> feeds;
  
}
