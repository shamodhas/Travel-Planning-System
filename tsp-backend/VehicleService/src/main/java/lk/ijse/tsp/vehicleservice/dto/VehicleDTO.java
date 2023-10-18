package lk.ijse.tsp.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String vehicleId;
    private String brand;
    private String category;
    private String fuelType;
    private boolean isHybrid;
    private double fuelUsagePerKM;

    private byte[] vehicleFrontImage;
    private byte[] vehicleRearImage;
    private byte[] vehicleSideImage;
    private byte[] vehicleFrontInteriorImage;
    private byte[] vehicleRearInteriorImage;

    private BigDecimal pricePerKM;
    private int capacity;
    private String type;
    private String transmission;
    private String driverName;

    private byte[] driverLicenseFrontImage;
    private byte[] driverLicenseBackImage;

    private String phone;
}
