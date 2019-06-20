package hiring.exercise.rssanalyzer.exception;

public class UnparseableXmlException extends RuntimeException {

  private static final String UNPARSEABLE_FEED = "Feed scheme could not be parse";

  public UnparseableXmlException() {
    super(UNPARSEABLE_FEED);
  }
  
}
