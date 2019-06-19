package hiring.exercise.rssanalyzer.facade;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feed {
  
  private String title;
  private String link;
  private String description;
  private String language;
  private String copyright;
  private String pubDate;

  private List<FeedMessage> entries = new ArrayList<FeedMessage>();
  
}
