package hiring.exercise.rssanalyzer.facade;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class RssFeedFacade {
  
  public SyndFeed getRssFeed(String url) {
    try {
      URL feedSource = new URL(url);
      SyndFeedInput input = new SyndFeedInput();
      return input.build(new XmlReader(feedSource));
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FeedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
  
}
