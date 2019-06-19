package hiring.exercise.rssanalyzer.facade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedMessage {
  
  private String title;
  private String description;
  private String link;
  private String author;
  private String guid;
  
}
