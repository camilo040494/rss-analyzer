package hiring.exercise.rssanalyzer.exception;

import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
  
  @JsonInclude(Include.NON_NULL) 
  private String object;
   private String field;
   @JsonInclude(Include.NON_NULL)
   private Object rejectedValue;
   private String message;

   public ApiValidationError(String object, String message) {
       this.object = object;
       this.message = message;
   }
   
   public ApiValidationError(ObjectError objectError) {
     this.field = objectError.getObjectName();
     this.message = objectError.getDefaultMessage();
   }
}
