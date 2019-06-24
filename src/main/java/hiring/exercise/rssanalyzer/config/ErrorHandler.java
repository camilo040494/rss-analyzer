package hiring.exercise.rssanalyzer.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nimbusds.oauth2.sdk.ErrorObject;

import hiring.exercise.rssanalyzer.exception.ApiError;
import hiring.exercise.rssanalyzer.exception.ApiSubError;
import hiring.exercise.rssanalyzer.exception.ApiValidationError;
import hiring.exercise.rssanalyzer.exception.business.NonExistentIdException;
import hiring.exercise.rssanalyzer.exception.business.UnRecognizedFeedException;
import hiring.exercise.rssanalyzer.exception.business.UnRecognizedUrlFeedException;
import hiring.exercise.rssanalyzer.exception.business.UnparseableXmlException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = { UnRecognizedFeedException.class, UnparseableXmlException.class,
      UnRecognizedUrlFeedException.class })
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    String error = "Exception while parsing the url";
    return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, error, ex));
  }

  @ExceptionHandler(value = { NonExistentIdException.class })
  protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
    String error = "Unexistent Id";
    return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
  }
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    super.handleMethodArgumentNotValid(ex, headers, status, request);
    String error = "Malformed JSON request";
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
    apiError.setMessage(error);
    for (ObjectError iterable_element : ex.getBindingResult().getAllErrors()) {
      apiError.addSubError(new ApiValidationError(iterable_element));
    }
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}