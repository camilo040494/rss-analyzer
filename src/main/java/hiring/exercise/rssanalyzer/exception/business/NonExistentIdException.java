package hiring.exercise.rssanalyzer.exception.business;

public class NonExistentIdException extends RuntimeException {

  private static final String COULD_NOT_FOUND_SPECIFIED_TRANSACTION = "Could not found specified transaction {id=%s}";

  public NonExistentIdException(int id) {
    super(String.format(COULD_NOT_FOUND_SPECIFIED_TRANSACTION, id));
  }
  
}
