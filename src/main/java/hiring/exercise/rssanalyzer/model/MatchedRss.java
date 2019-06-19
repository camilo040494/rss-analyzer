package hiring.exercise.rssanalyzer.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "matchedrss")
@Entity
public class MatchedRss {
  
  @Id
  @GeneratedValue
  private int id;
  
  @OneToMany
  private List<RssData> data;
  
  public void addRssData(RssData rssData) {
    data.add(rssData);
    rssData.setMatchedRss(this);
  }
  
}
