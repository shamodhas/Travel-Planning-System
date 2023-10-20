package lk.ijse.tps.tps.hotelservice.handler;

import lk.ijse.tps.tps.hotelservice.exception.DuplicateException;
import lk.ijse.tps.tps.hotelservice.exception.InUseException;
import lk.ijse.tps.tps.hotelservice.exception.InvalidException;
import lk.ijse.tps.tps.hotelservice.exception.NotFoundException;
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
        return ResponseEntity.badRequest().body("Duplicate case\n "+exception.getMessage());
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body("In use case\n "+exception.getMessage());
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body("In valid case\n "+exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body("Not found case\n "+exception.getMessage());
    }
}
