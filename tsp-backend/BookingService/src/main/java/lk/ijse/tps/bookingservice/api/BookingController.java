package lk.ijse.tps.bookingservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.bookingservice.exception.InvalidException;
import lk.ijse.tps.bookingservice.dto.VehicleBookingDTO;
import lk.ijse.tps.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("{bookingId:^[B][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getSelectedBooking(@PathVariable String bookingId) {
        System.out.println(bookingId);
        return ResponseEntity.ok(bookingService.getSelectedBooking(bookingId));
    }

    @GetMapping("{customerId:^[U][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getAllBookingByCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(bookingService.getAllBookingByCustomerId(customerId));
    }

    @GetMapping("{packageId:^[P][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getAllBookingByPackage(@PathVariable String packageId) {
        return ResponseEntity.ok(bookingService.getAllBookingByPackageId(packageId));
    }

    @GetMapping("{guideId:^[G][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getAllBookingByGuide(@PathVariable String guideId) {
        return ResponseEntity.ok(bookingService.getAllBookingByGuideId(guideId));
    }

    @GetMapping("{hotelOptionId:^[HOP][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getAllBookingByHotelOption(@PathVariable String hotelOptionId) {
        return ResponseEntity.ok(bookingService.getAllBookingByHotelOptionId(hotelOptionId));
    }

    @GetMapping("{vehicleId:^[V][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getAllBookingByVehicle(@PathVariable String vehicleId) {
        return ResponseEntity.ok(bookingService.getAllBookingByVehicleId(vehicleId));
    }

    @GetMapping("{date:^\\d{4}-\\d{2}-\\d{2}$}")
    ResponseEntity<?> getAllBookingByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(bookingService.getAllBookingByDate(date));
    }

    @GetMapping
    ResponseEntity<?> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAllBooking());
    }

    @PostMapping
    ResponseEntity<?> saveBooking(@Valid @RequestBody BookingDTO bookingDTO, Errors errors) {
        System.out.println(bookingDTO);
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        return ResponseEntity.ok(bookingService.addBooking(bookingDTO));
    }

    @PutMapping("{bookingId}")
    ResponseEntity<?> updateBooking(@PathVariable String bookingId, @Valid @RequestBody BookingDTO bookingDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        bookingDTO.setBookingId(bookingId);
        bookingDTO.getVehicleBookings().forEach(vehicleBookingDTO -> vehicleBookingDTO.setBookingId(bookingId));
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
