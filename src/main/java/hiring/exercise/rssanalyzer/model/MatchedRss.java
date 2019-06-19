package hiring.exercise.rssanalyzer.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchedRss {
  
  private int id;
  private List<RssDetail> data;
  
}
