package hiring.exercise.rssanalyzer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "application.policies")
@Data
public class IntersectionConstraintProperties {
  
  private Boolean intersection;
  
}
