package hiring.exercise.rssanalyzer.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class OkResponse<T> extends ResponseEntity<T>{
  
  private OkResponse(HttpStatus status) {
    super(status);
  }
  
  public OkResponse(T body) {
    this(HttpStatus.OK);
  }
  
}
