package hiring.exercise.rssanalyzer.exception.business;

public class UnRecognizedUrlFeedException extends RuntimeException {
  
  private static final String ERROR_INVALID_URL = "Specified url [%s] is not a valid feeds";

  public UnRecognizedUrlFeedException(String url) {
    super(String.format(ERROR_INVALID_URL, url));
  }
  
}
