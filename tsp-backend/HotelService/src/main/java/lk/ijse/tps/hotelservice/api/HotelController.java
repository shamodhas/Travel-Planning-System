package lk.ijse.tps.hotelservice.api;

import lk.ijse.tps.hotelservice.dto.HotelDTO;
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
@RequestMapping("api/v1/hotel")
@CrossOrigin("*")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping
    ResponseEntity<?> getSelectedHotel(String hotelId) {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllHotel() {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveHotel(HotelDTO hotelDTO) {
        return null;
    }

    @PutMapping
    ResponseEntity<?> updateHotel(String hotelId, HotelDTO hotelDTO) {
        return null;
    }

    @DeleteMapping
    ResponseEntity<?> deleteHotel(String hotelId) {
        return null;
    }
}
