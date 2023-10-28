package lk.ijse.tps.tps.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.tps.hotelservice.dto.HotelOptionDTO;
import lk.ijse.tps.tps.hotelservice.exception.InvalidException;
import lk.ijse.tps.tps.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
//@CrossOrigin("*")
@RequiredArgsConstructor
public class HotelOptionController {
    private final HotelService hotelService;

    @GetMapping("{hotelOptionId}")
    ResponseEntity<?> getSelectedHotelOption(@PathVariable String hotelOptionId) {
        return ResponseEntity.ok(hotelService.getSelectedHotelOption(hotelOptionId));
    }

    @GetMapping("/public")
    ResponseEntity<?> getAllHotelOption() {
        return ResponseEntity.ok(hotelService.getAllHotelOption());
    }

    @PostMapping
    ResponseEntity<?> saveHotelOption(@Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        return ResponseEntity.ok(hotelService.addHotelOption(hotelOptionDTO));
    }

    @PutMapping("{hotelOptionId}")
    ResponseEntity<?> updateHotelOption(@PathVariable String hotelOptionId, @Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
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
