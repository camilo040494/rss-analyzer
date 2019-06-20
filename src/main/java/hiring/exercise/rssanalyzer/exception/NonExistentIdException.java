package hiring.exercise.rssanalyzer.exception;

public class NonExistentIdException extends RuntimeException {

  public NonExistentIdException() {
    super("Could not found specified transaction");
  }
  
}
