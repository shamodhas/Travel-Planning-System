package lk.ijse.tps.bookingservice.api;

import lk.ijse.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.bookingservice.dto.VehicleBookingDTO;
import lk.ijse.tps.bookingservice.exception.InvalidException;
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
        return ResponseEntity.ok(bookingService.getSelectedBooking(bookingId));
    }

    @GetMapping
    ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAllBooking());
    }

    @GetMapping
    ResponseEntity<?> getAllBookingByBookingDTO(@RequestBody BookingDTO bookingDTO) {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveBooking(@RequestBody BookingDTO bookingDTO) {
        // validation need
        return ResponseEntity.ok(bookingService.addBooking(bookingDTO));
    }

    @PutMapping("{bookingId}")
    ResponseEntity<?> updateBooking(@PathVariable String bookingId, @RequestBody BookingDTO bookingDTO) {
        // validation need
        bookingDTO.setBookingId(bookingId);
        bookingService.updateBooking(bookingDTO);
        return ResponseEntity.ok("Booking updated");
    }

    @DeleteMapping("{bookingId}")
    ResponseEntity<?> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking deleted");
    }

    @DeleteMapping
    ResponseEntity<?> deleteBooking(@RequestBody VehicleBookingDTO vehicleBookingDTO) {
        bookingService.deleteVehicleBooking(vehicleBookingDTO.getBookingId(), vehicleBookingDTO.getVehicleId());
        return ResponseEntity.ok("Booking deleted");
    }
}
