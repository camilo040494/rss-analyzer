package hiring.exercise.rssanalyzer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
  
  @OneToMany(cascade = CascadeType.ALL)
  private List<RssData> data = new ArrayList<RssData>();
  
  public void addRssData(RssData rssData) {
    data.add(rssData);
    rssData.setMatchedRss(this);
  }
  
}
