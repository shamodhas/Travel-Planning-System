package lk.ijse.tps.vehicleservice.api;

import lk.ijse.tps.vehicleservice.dto.VehicleDTO;
import lk.ijse.tps.vehicleservice.exception.InvalidException;
import lk.ijse.tps.vehicleservice.service.VehicleService;
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
@RequestMapping("api/v1/vehicle")
@CrossOrigin("*")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("{vehicleId}")
    ResponseEntity<?> getSelectedVehicle(@PathVariable String vehicleId) {
        return ResponseEntity.ok(vehicleService.getSelectedVehicle(vehicleId));
    }

    @GetMapping
    ResponseEntity<?> getAllVehicle() {
        return ResponseEntity.ok(vehicleService.getAllVehicle());
    }

    @PostMapping
    ResponseEntity<?> saveVehicle(
            @RequestPart String vehicleLicenseNumber,
            @RequestPart String brand,
            @RequestPart String category,
            @RequestPart String fuelType,
            @RequestPart String isHybrid,
            @RequestPart String fuelUsagePerKM,
            @RequestPart byte[] vehicleFrontImage,
            @RequestPart byte[] vehicleRearImage,
            @RequestPart byte[] vehicleSideImage,
            @RequestPart byte[] vehicleFrontInteriorImage,
            @RequestPart byte[] vehicleRearInteriorImage,
            @RequestPart String pricePerKM,
            @RequestPart String capacity,
            @RequestPart String type,
            @RequestPart String transmission,
            @RequestPart String driverName,
            @RequestPart byte[] driverLicenseFrontImage,
            @RequestPart byte[] driverLicenseBackImage,
            @RequestPart String phone
    ) {
        if (vehicleLicenseNumber == null)
            throw new InvalidException("InValid vehicle license number");
        if (brand == null)
            throw new InvalidException("InValid brand name");
        if (category == null)
            throw new InvalidException("InValid category");
        if (fuelType == null)
            throw new InvalidException("InValid fuel type");
        if (isHybrid == null)
            throw new InvalidException("InValid boolean for isHybrid");
        if (fuelUsagePerKM == null)
            throw new InvalidException("InValid fuel usage per km");
        try {
            if (Double.parseDouble(fuelUsagePerKM) <= 0)
                throw new InvalidException("InValid fuel usage per km");
        } catch (NumberFormatException e) {
            throw new InvalidException("InValid fuel usage per km");
        }
        if (vehicleFrontImage.length == 0)
            throw new InvalidException("InValid vehicle front image");
        if (vehicleRearImage.length == 0)
            throw new InvalidException("InValid vehicle rear image");
        if (vehicleSideImage.length == 0)
            throw new InvalidException("InValid vehicle side image");
        if (vehicleFrontInteriorImage.length == 0)
            throw new InvalidException("InValid vehicle front interior image");
        if (vehicleRearInteriorImage.length == 0)
            throw new InvalidException("InValid vehicle rear interior image");
        if (pricePerKM == null || !Pattern.matches("^(\\d+)||((\\d+\\.)(\\d){2})$", pricePerKM))
            throw new InvalidException("InValid price per km");
        if (capacity == null)
            throw new InvalidException("InValid capacity");
        try {
            if (Integer.parseInt(capacity) <= 0)
                throw new InvalidException("InValid capacity");
        } catch (NumberFormatException e) {
            throw new InvalidException("InValid capacity");
        }
        if (type == null)
            throw new InvalidException("InValid type");
        if (transmission == null)
            throw new InvalidException("InValid transmission");
        if (driverName == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$",driverName))
            throw new InvalidException("InValid driver name");
        if (driverLicenseFrontImage.length == 0)
            throw new InvalidException("InValid driver license front image");
        if (driverLicenseBackImage.length == 0)
            throw new InvalidException("InValid driver license back image");
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$",phone))
            throw new InvalidException("InValid phone number");

        return ResponseEntity.ok(vehicleService.saveVehicle(
                VehicleDTO.builder()
                        .vehicleLicenseNumber(vehicleLicenseNumber)
                        .brand(brand)
                        .category(category)
                        .fuelType(fuelType)
                        .isHybrid(Boolean.parseBoolean(isHybrid))
                        .fuelUsagePerKM(Double.parseDouble(fuelUsagePerKM))
                        .vehicleFrontImage(vehicleFrontImage)
                        .vehicleRearImage(vehicleRearImage)
                        .vehicleSideImage(vehicleSideImage)
                        .vehicleFrontInteriorImage(vehicleFrontInteriorImage)
                        .vehicleRearInteriorImage(vehicleRearInteriorImage)
                        .pricePerKM(new BigDecimal(pricePerKM))
                        .capacity(Integer.parseInt(capacity))
                        .type(type)
                        .transmission(transmission)
                        .driverName(driverName)
                        .driverLicenseFrontImage(driverLicenseFrontImage)
                        .driverLicenseBackImage(driverLicenseBackImage)
                        .phone(phone)
                        .build()
        ));
    }

    @PutMapping("{vehicleId}")
    ResponseEntity<?> updateVehicle(
            @PathVariable String vehicleId,
            @RequestPart String vehicleLicenseNumber,
            @RequestPart String brand,
            @RequestPart String category,
            @RequestPart String fuelType,
            @RequestPart String isHybrid,
            @RequestPart String fuelUsagePerKM,
            @RequestPart byte[] vehicleFrontImage,
            @RequestPart byte[] vehicleRearImage,
            @RequestPart byte[] vehicleSideImage,
            @RequestPart byte[] vehicleFrontInteriorImage,
            @RequestPart byte[] vehicleRearInteriorImage,
            @RequestPart String pricePerKM,
            @RequestPart String capacity,
            @RequestPart String type,
            @RequestPart String transmission,
            @RequestPart String driverName,
            @RequestPart byte[] driverLicenseFrontImage,
            @RequestPart byte[] driverLicenseBackImage,
            @RequestPart String phone
    ) {
        if (vehicleLicenseNumber == null)
            throw new InvalidException("InValid vehicle license number");
        if (brand == null)
            throw new InvalidException("InValid brand name");
        if (category == null)
            throw new InvalidException("InValid category");
        if (fuelType == null)
            throw new InvalidException("InValid fuel type");
        if (isHybrid == null)
            throw new InvalidException("InValid boolean for isHybrid");
        if (fuelUsagePerKM == null)
            throw new InvalidException("InValid fuel usage per km");
        try {
            if (Double.parseDouble(fuelUsagePerKM) <= 0)
                throw new InvalidException("InValid fuel usage per km");
        } catch (NumberFormatException e) {
            throw new InvalidException("InValid fuel usage per km");
        }
        if (vehicleFrontImage.length == 0)
            throw new InvalidException("InValid vehicle front image");
        if (vehicleRearImage.length == 0)
            throw new InvalidException("InValid vehicle rear image");
        if (vehicleSideImage.length == 0)
            throw new InvalidException("InValid vehicle side image");
        if (vehicleFrontInteriorImage.length == 0)
            throw new InvalidException("InValid vehicle front interior image");
        if (vehicleRearInteriorImage.length == 0)
            throw new InvalidException("InValid vehicle rear interior image");
        if (pricePerKM == null || !Pattern.matches("^(\\d+)||((\\d+\\.)(\\d){2})$", pricePerKM))
            throw new InvalidException("InValid price per km");
        if (capacity == null)
            throw new InvalidException("InValid capacity");
        try {
            if (Integer.parseInt(capacity) <= 0)
                throw new InvalidException("InValid capacity");
        } catch (NumberFormatException e) {
            throw new InvalidException("InValid capacity");
        }
        if (type == null)
            throw new InvalidException("InValid type");
        if (transmission == null)
            throw new InvalidException("InValid transmission");
        if (driverName == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$",driverName))
            throw new InvalidException("InValid driver name");
        if (driverLicenseFrontImage.length == 0)
            throw new InvalidException("InValid driver license front image");
        if (driverLicenseBackImage.length == 0)
            throw new InvalidException("InValid driver license back image");
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$",phone))
            throw new InvalidException("InValid phone number");

        vehicleService.updateVehicle(
                VehicleDTO.builder()
                        .vehicleId(vehicleId)
                        .vehicleLicenseNumber(vehicleLicenseNumber)
                        .brand(brand)
                        .category(category)
                        .fuelType(fuelType)
                        .isHybrid(Boolean.parseBoolean(isHybrid))
                        .fuelUsagePerKM(Double.parseDouble(fuelUsagePerKM))
                        .vehicleFrontImage(vehicleFrontImage)
                        .vehicleRearImage(vehicleRearImage)
                        .vehicleSideImage(vehicleSideImage)
                        .vehicleFrontInteriorImage(vehicleFrontInteriorImage)
                        .vehicleRearInteriorImage(vehicleRearInteriorImage)
                        .pricePerKM(new BigDecimal(pricePerKM))
                        .capacity(Integer.parseInt(capacity))
                        .type(type)
                        .transmission(transmission)
                        .driverName(driverName)
                        .driverLicenseFrontImage(driverLicenseFrontImage)
                        .driverLicenseBackImage(driverLicenseBackImage)
                        .phone(phone)
                        .build()
        );
        return ResponseEntity.ok("Vehicle updated");
    }

    @DeleteMapping("{vehicleId}")
    ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted");
    }

}
