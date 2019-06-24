package hiring.exercise.rssanalyzer.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  @JsonInclude(Include.NON_EMPTY)
  private List<ApiSubError> subErrors = new ArrayList<ApiSubError>();

  private ApiError() {
      timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status) {
      this();
      this.status = status;
  }

  public ApiError(HttpStatus status, Throwable ex) {
      this();
      this.status = status;
      this.message = "Unexpected error";
      this.debugMessage = ex.getLocalizedMessage();
  }

  public ApiError(HttpStatus status, String message, Throwable ex) {
      this();
      this.status = status;
      this.message = message;
      this.debugMessage = ex.getLocalizedMessage();
  }
  
  public void addSubError(ApiSubError subError) {
    subErrors.add(subError);
  }
  
}
