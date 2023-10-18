package lk.ijse.tps.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.hotelservice.dto.HotelOptionDTO;
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
@RequestMapping("api/v1/hotelOption")
@CrossOrigin("*")
public class HotelOptionController {
    @Autowired
    HotelService hotelService;

    @GetMapping("{hotelOptionId}")
    ResponseEntity<?> getSelectedHotelOption(@PathVariable String hotelOptionId) {
        return ResponseEntity.ok(hotelService.getSelectedHotelOption(hotelOptionId));
    }

    @GetMapping
    ResponseEntity<?> getAllHotelOption() {
        return ResponseEntity.ok(hotelService.getAllHotelOption());
    }

    @PostMapping
    ResponseEntity<?> saveHotelOption(@Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors())
            throw new InvalidException(errors.getAllErrors().toString());
        return ResponseEntity.ok(hotelService.addHotelOption(hotelOptionDTO));
    }

    @PutMapping("{hotelOptionId}")
    ResponseEntity<?> updateHotelOption(@PathVariable String hotelOptionId, @Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors())
            throw new InvalidException(errors.getAllErrors().toString());
        hotelOptionDTO.setHotelOptionId(hotelOptionId);
        hotelService.updateHotelOption(hotelOptionDTO);
        return ResponseEntity.ok("Hotel option updated");
    }

    @DeleteMapping("{hotelOptionId}")
    ResponseEntity<?> deleteHotelOption(@PathVariable String hotelOptionId) {
        hotelService.deleteHotelOption(hotelOptionId);
        return ResponseEntity.ok("Hotel option deleted");
    }
}
