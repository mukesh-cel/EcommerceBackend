package auth.example.Authentication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handlecustomException(CustomException customException){
        return  new ResponseEntity<>(customException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
