package ch.welld.patternrecognition.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = SamePointException.class)
    public ResponseEntity<?> SamePointException(SamePointException exception){
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}