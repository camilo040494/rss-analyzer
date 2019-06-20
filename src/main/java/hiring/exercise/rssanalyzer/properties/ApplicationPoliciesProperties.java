package hiring.exercise.rssanalyzer.properties;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "application.policies")
@Data
public class ApplicationPoliciesProperties {
  
  private Boolean intersectionThroughLinks;
  private Integer maxNumberResults;
  private Set<String> blackListWords;
  
}
