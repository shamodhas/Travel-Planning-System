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

    @GetMapping("{bookingId}")
    ResponseEntity<?> getSelectedBooking(@PathVariable String bookingId) {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllBooking() {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllBookingByBookingDTO(@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveBooking(@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    @PutMapping("{bookingId}")
    ResponseEntity<?> updateBooking(@PathVariable String bookingId,@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    @DeleteMapping("{bookingId}")
    ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
        return null;
    }
}
