package hiring.exercise.rssanalyzer.util.validator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.amazonaws.util.CollectionUtils;

public class ListValidatorConstraint implements ConstraintValidator<ListValidator, List<String>>{

  private static final int MINIMUM_ITEMS_IN_LIST = 2;

  @Override
  public void initialize(ListValidator constraintAnnotation) {
    //
  }
  
  @Override
  public boolean isValid(List<String> urlCollection, ConstraintValidatorContext context) {
    if (CollectionUtils.isNullOrEmpty(urlCollection)
        || urlCollection.size() < MINIMUM_ITEMS_IN_LIST) {
      return false;
    }
    for (String url : urlCollection) {
      try {
        new URL(url);        
      } catch (MalformedURLException e) {
        context.disableDefaultConstraintViolation();
        context
            .buildConstraintViolationWithTemplate(String.format("Malformed url: [%s]", url))
            .addConstraintViolation();
        return false;
      }
    }
    
    return true;
  }
  

}
