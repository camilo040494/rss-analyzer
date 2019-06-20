package hiring.exercise.rssanalyzer.exception;

public class UnRecognizedFeedException extends RuntimeException {
  
  private static final String FEED_ERROR = "Feed type was not supported correctly";

  public UnRecognizedFeedException() {
    super(FEED_ERROR);
  }
  
}
