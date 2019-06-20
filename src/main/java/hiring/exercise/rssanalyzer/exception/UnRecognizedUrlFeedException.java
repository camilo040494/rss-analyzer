package hiring.exercise.rssanalyzer.exception;

public class UnRecognizedUrlFeedException extends RuntimeException {
  
  private static final String ERROR_INVALID_URL = "Specified url's are not valid feeds";

  public UnRecognizedUrlFeedException() {
   super(ERROR_INVALID_URL);
  }
  
}
