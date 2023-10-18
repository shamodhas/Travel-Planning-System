package lk.ijse.tps.bookingservice.api;

import lk.ijse.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping
    ResponseEntity<?> getSelectedBooking(String bookingId) {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllBooking() {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveBooking(BookingDTO bookingDTO) {
        return null;
    }

    @PostMapping
    ResponseEntity<?> updateBooking(String bookingId, BookingDTO bookingDTO) {
        return null;
    }

    @DeleteMapping
    ResponseEntity<?> deleteBooking(String bookingId) {
        return null;
    }
}
