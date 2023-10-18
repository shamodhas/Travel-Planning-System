package lk.ijse.tps.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.hotelservice.exception.InvalidException;
import lk.ijse.tps.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/hotel")
@CrossOrigin("*")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("{hotelId}")
    ResponseEntity<?> getSelectedHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getSelectedHotel(hotelId));
    }

    @GetMapping
    ResponseEntity<?> getAllHotel() {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }

    @PostMapping
    ResponseEntity<?> saveHotel(@Valid @RequestBody HotelDTO hotelDTO, Errors errors) {
        if (errors.hasErrors())
            throw new InvalidException(errors.getAllErrors().toString());
        return ResponseEntity.ok(hotelService.saveHotel(hotelDTO));
    }

    @PutMapping("{hotelId}")
    ResponseEntity<?> updateHotel(@PathVariable String hotelId,@Valid @RequestBody HotelDTO hotelDTO,Errors errors) {
        if (errors.hasErrors())
            throw new InvalidException(errors.getAllErrors().toString());
        hotelDTO.setHotelId(hotelId);
        hotelService.updateHotel(hotelDTO);
        return ResponseEntity.ok("Hotel updated");
    }

    @DeleteMapping("{hotelId}")
    ResponseEntity<?> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok("Hotel deleted");
    }
}
