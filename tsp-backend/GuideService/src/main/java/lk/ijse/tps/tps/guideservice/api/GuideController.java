package lk.ijse.tps.tps.guideservice.api;

import lk.ijse.tps.tps.guideservice.dto.GuideDTO;
import lk.ijse.tps.tps.guideservice.exception.InvalidException;
import lk.ijse.tps.tps.guideservice.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/guide")
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;

    @GetMapping("{guideId}")
    ResponseEntity<?> getSelectedGuide(@PathVariable String guideId) {
        return ResponseEntity.ok(guideService.getSelectedGuide(guideId));
    }

    @GetMapping("/public")
    ResponseEntity<?> getAllGuide() {
        return ResponseEntity.ok(guideService.getAllGuide());
    }

    @PostMapping
    ResponseEntity<?> saveGuide(
            @RequestPart String name,
            @RequestPart String address,
            @RequestPart String nic,
            @RequestPart String phone,
            @RequestPart byte[] profile,
            @RequestPart byte[] idCardFront,
            @RequestPart byte[] idCardBack,
            @RequestPart String pricePerDay
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (address == null)
            throw new InvalidException("InValid address");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", phone))
            throw new InvalidException("InValid phone");
        if (profile.length == 0)
            throw new InvalidException("InValid profile image");
        if (idCardFront.length == 0)
            throw new InvalidException("InValid id card front image");
        if (idCardBack.length == 0)
            throw new InvalidException("InValid id card back image");
        if (pricePerDay == null || !Pattern.matches("^(\\d+)||((\\d+\\.)(\\d){2})$", pricePerDay))
            throw new InvalidException("InValid price per day");

        return ResponseEntity.ok(guideService.saveGuide(
                GuideDTO.builder()
                        .name(name)
                        .address(address)
                        .nic(nic)
                        .phone(phone)
                        .profile(profile)
                        .idCardFront(idCardFront)
                        .idCardBack(idCardBack)
                        .pricePerDay(new BigDecimal(pricePerDay))
                        .build()
        ));
    }

    @PutMapping("{guideId}")
    ResponseEntity<?> updateGuide(
            @PathVariable String guideId,
            @RequestPart String name,
            @RequestPart String address,
            @RequestPart String nic,
            @RequestPart String phone,
            @RequestPart byte[] profile,
            @RequestPart byte[] idCardFront,
            @RequestPart byte[] idCardBack,
            @RequestPart String pricePerDay
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (address == null)
            throw new InvalidException("InValid address");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", phone))
            throw new InvalidException("InValid phone");
        if (profile.length == 0)
            throw new InvalidException("InValid profile image");
        if (idCardFront.length == 0)
            throw new InvalidException("InValid id card front image");
        if (idCardBack.length == 0)
            throw new InvalidException("InValid id card back image");
        if (pricePerDay == null || !Pattern.matches("^(\\d+)||((\\d+\\.)(\\d){2})$", pricePerDay))
            throw new InvalidException("InValid price per day");

        guideService.updateGuide(
                GuideDTO.builder()
                        .guideId(guideId)
                        .name(name)
                        .address(address)
                        .nic(nic)
                        .phone(phone)
                        .profile(profile)
                        .idCardFront(idCardFront)
                        .idCardBack(idCardBack)
                        .pricePerDay(new BigDecimal(pricePerDay))
                        .build()
        );
        return ResponseEntity.ok("Guide updated");
    }

    @DeleteMapping("{guideId}")
    ResponseEntity<?> deleteGuide(@PathVariable String guideId) {
        guideService.deleteGuide(guideId);
        return ResponseEntity.ok("Guide deleted");
    }
}
