package lk.ijse.tsp.vehicleservice.api;

import lk.ijse.tsp.vehicleservice.dto.VehicleDTO;
import lk.ijse.tsp.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin("*")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

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
        // validation set need to dto class null value not go
//        isHybrid to boolean
//        fuelUsagePerKM check can convert double
//        pricePerKM convert to big decimal
//        capacity to int
        return ResponseEntity.ok(vehicleService.saveVehicle(
                VehicleDTO.builder()
                        .vehicleLicenseNumber(vehicleLicenseNumber)
                        .brand(brand)
                        .category(category)
                        .fuelType(fuelType)
                        .isHybrid(isHybrid.equals("true"))
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
            // validation set need to dto class null value not go
//        isHybrid to boolean
//        fuelUsagePerKM check can convert double
//        pricePerKM convert to big decimal
//        capacity to int
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
        vehicleService.updateVehicle(
                VehicleDTO.builder()
                        .vehicleId(vehicleId)
                        .vehicleLicenseNumber(vehicleLicenseNumber)
                        .brand(brand)
                        .category(category)
                        .fuelType(fuelType)
                        .isHybrid(isHybrid.equals("true"))
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
