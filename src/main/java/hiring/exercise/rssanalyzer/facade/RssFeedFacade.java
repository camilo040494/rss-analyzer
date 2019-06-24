package hiring.exercise.rssanalyzer.facade;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.ParsingFeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import hiring.exercise.rssanalyzer.exception.business.UnRecognizedFeedException;
import hiring.exercise.rssanalyzer.exception.business.UnRecognizedUrlFeedException;
import hiring.exercise.rssanalyzer.exception.business.UnparseableXmlException;

@Component
public class RssFeedFacade {
  
  public SyndFeed getRssFeed(String url) {
    try {
      URL feedSource = new URL(url);
      SyndFeedInput input = new SyndFeedInput();
      return input.build(new XmlReader(feedSource));
    } catch (IllegalArgumentException e) {
      throw new UnRecognizedFeedException();
    } catch (FeedException e) {
      throw new UnRecognizedUrlFeedException(url);
    } catch (IOException e) {
      throw new UnparseableXmlException();
    }
  }
  
}
