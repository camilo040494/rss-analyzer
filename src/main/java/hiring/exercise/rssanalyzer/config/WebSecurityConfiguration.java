package hiring.exercise.rssanalyzer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
        "/swagger-ui.html", "/rss-analyzer/**", "/swagger/ui/index", "/webjars/**", "/rss-analyzer/");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-resources/configuration/ui",
        "/swagger-ui.html", "/swagger-resources/configuration/security", "/rss-analyzer/").permitAll();
  }

}