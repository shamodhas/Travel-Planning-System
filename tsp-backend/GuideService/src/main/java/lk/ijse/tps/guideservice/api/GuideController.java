package lk.ijse.tps.guideservice.api;

import lk.ijse.tps.guideservice.dto.GuideDTO;
import lk.ijse.tps.guideservice.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/guide")
@CrossOrigin("*")
public class GuideController {
    @Autowired
    GuideService guideService;

    @GetMapping
    ResponseEntity<?> getSelectedGuide(String guideId) {
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllGuide() {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveGuide(GuideDTO guideDTO) {
        return null;
    }

    @PostMapping
    ResponseEntity<?> updateGuide(String guideId, GuideDTO guideDTO) {
        return null;
    }

    @DeleteMapping
    ResponseEntity<?> deleteGuide(String guideId) {
        return null;
    }
}
