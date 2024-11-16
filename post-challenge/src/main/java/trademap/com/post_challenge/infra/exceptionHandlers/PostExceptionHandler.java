package trademap.com.post_challenge.infra.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import trademap.com.post_challenge.domain.exceptions.PostNotFoundException;
import trademap.com.post_challenge.infra.exceptionHandlers.ResponseAPIRest.ResponseAPIRest;
import trademap.com.post_challenge.infra.exceptionHandlers.ResponseAPIRest.ResponseAPIRestBuilder;

@ControllerAdvice
public class PostExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ResponseAPIRest> postNotFoundExceptionHandler(PostNotFoundException e){
        ResponseAPIRest error = ResponseAPIRestBuilder.aResponseAPIRest()
                .withCode(HttpStatus.NOT_FOUND.value())
                .withName(HttpStatus.NOT_FOUND.name())
                .withError(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseAPIRest> IllegalArgumentExceptionHandler(IllegalArgumentException e){
        ResponseAPIRest error = ResponseAPIRestBuilder.aResponseAPIRest()
                .withCode(HttpStatus.BAD_REQUEST.value())
                .withName(HttpStatus.BAD_REQUEST.name())
                .withError(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
