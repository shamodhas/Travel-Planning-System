package lk.ijse.authservice.handler;

import lk.ijse.authservice.exception.DuplicateException;
import lk.ijse.authservice.exception.InUseException;
import lk.ijse.authservice.exception.InvalidException;
import lk.ijse.authservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/20/2023
 * Time : 11:34 PM
 */

@ControllerAdvice
public class GlobeVideExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
