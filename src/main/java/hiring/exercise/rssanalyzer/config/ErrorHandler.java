package hiring.exercise.rssanalyzer.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hiring.exercise.rssanalyzer.exception.NonExistentIdException;
import hiring.exercise.rssanalyzer.exception.UnRecognizedFeedException;
import hiring.exercise.rssanalyzer.exception.UnRecognizedUrlFeedException;
import hiring.exercise.rssanalyzer.exception.UnparseableXmlException;

@ControllerAdvice
public class ErrorHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value
      = { UnRecognizedFeedException.class, UnparseableXmlException.class,
          UnRecognizedUrlFeedException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(value
        = { NonExistentIdException.class })
    protected ResponseEntity<Object> handleNotFound(
        RuntimeException ex, WebRequest request) {
      String bodyOfResponse = "This should be application specific";
      return handleExceptionInternal(ex, bodyOfResponse,
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}