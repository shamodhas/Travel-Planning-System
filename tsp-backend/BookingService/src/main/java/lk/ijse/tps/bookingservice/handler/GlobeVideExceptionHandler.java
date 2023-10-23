package lk.ijse.tps.bookingservice.handler;

import lk.ijse.tps.bookingservice.exception.DuplicateException;
import lk.ijse.tps.bookingservice.exception.InUseException;
import lk.ijse.tps.bookingservice.exception.InvalidException;
import lk.ijse.tps.bookingservice.exception.NotFoundException;
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
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
}
