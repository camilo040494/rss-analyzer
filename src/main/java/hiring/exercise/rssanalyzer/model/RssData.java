package hiring.exercise.rssanalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "rssdata")
@Entity
public class RssData {
  
  @Id
  @GeneratedValue
  private int id;
  @Column
  private String header;
  @Column
  private String link;
  @ManyToOne
  @JoinColumn(name = "matched_id")
  private MatchedRss matchedRss;
 
}
