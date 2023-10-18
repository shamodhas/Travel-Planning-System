package lk.ijse.tps.hotelservice.api;

import lk.ijse.tps.hotelservice.dto.HotelOptionDTO;
import lk.ijse.tps.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    ResponseEntity<?> getSelectedHotelOption(String hotelOptionId) {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllHotelOption() {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveHotelOption(HotelOptionDTO hotelOptionDTO) {
        return null;
    }

    @PutMapping
    ResponseEntity<?> updateHotelOption(String hotelOptionId, HotelOptionDTO hotelOptionDTO) {
        return null;
    }

    @DeleteMapping
    ResponseEntity<?> deleteHotelOption(String hotelOptionId) {
        return null;
    }
}
