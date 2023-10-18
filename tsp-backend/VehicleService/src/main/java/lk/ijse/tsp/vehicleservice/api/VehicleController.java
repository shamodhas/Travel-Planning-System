package lk.ijse.tsp.vehicleservice.api;

import lk.ijse.tsp.vehicleservice.dto.VehicleDTO;
import lk.ijse.tsp.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return null;
    }

    @GetMapping
    ResponseEntity<?> getAllVehicle() {
        return null;
    }

    @PostMapping
    ResponseEntity<?> saveVehicle(
            @RequestPart String brand,
            @RequestPart String category,
            @RequestPart String fuelType,
            @RequestPart boolean isHybrid,
            @RequestPart double fuelUsagePerKM,
            @RequestPart byte[] vehicleFrontImage,
            @RequestPart byte[] vehicleRearImage,
            @RequestPart byte[] vehicleSideImage,
            @RequestPart byte[] vehicleFrontInteriorImage,
            @RequestPart byte[] vehicleRearInteriorImage,
            @RequestPart BigDecimal pricePerKM,
            @RequestPart int capacity,
            @RequestPart String type,
            @RequestPart String transmission,
            @RequestPart String driverName,
            @RequestPart byte[] driverLicenseFrontImage,
            @RequestPart byte[] driverLicenseBackImage,
            @RequestPart String phone
    ) {
        return null;
    }

    @PutMapping("{vehicleId}")
    ResponseEntity<?> updateVehicle(
            @PathVariable String vehicleId,
            @RequestPart String brand,
            @RequestPart String category,
            @RequestPart String fuelType,
            @RequestPart boolean isHybrid,
            @RequestPart double fuelUsagePerKM,
            @RequestPart byte[] vehicleFrontImage,
            @RequestPart byte[] vehicleRearImage,
            @RequestPart byte[] vehicleSideImage,
            @RequestPart byte[] vehicleFrontInteriorImage,
            @RequestPart byte[] vehicleRearInteriorImage,
            @RequestPart BigDecimal pricePerKM,
            @RequestPart int capacity,
            @RequestPart String type,
            @RequestPart String transmission,
            @RequestPart String driverName,
            @RequestPart byte[] driverLicenseFrontImage,
            @RequestPart byte[] driverLicenseBackImage,
            @RequestPart String phone
    ) {
        return null;
    }

    @DeleteMapping("{vehicleId}")
    ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        return null;
    }

}
