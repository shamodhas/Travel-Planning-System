package lk.ijse.tps.tps.hotelservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.tps.hotelservice.dto.HotelDTO;
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
@RequestMapping("api/v1/hotel")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("{hotelId}")
    ResponseEntity<?> getSelectedHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(hotelService.getSelectedHotel(hotelId));
    }

    @GetMapping("/public")
    ResponseEntity<?> getAllHotel() {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }

    @PostMapping
    ResponseEntity<?> saveHotel(@Valid @RequestBody HotelDTO hotelDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        System.out.println(hotelDTO);
        return ResponseEntity.ok(hotelService.saveHotel(hotelDTO));
    }

    @PutMapping("{hotelId}")
    ResponseEntity<?> updateHotel(@PathVariable String hotelId,@Valid @RequestBody HotelDTO hotelDTO,Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
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
