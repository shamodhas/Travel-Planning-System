package lk.ijse.tps.bookingservice.exception;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:26 PM
 */

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
