package lk.ijse.tps.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehicleDTO {
    private String vehicleId;
    private String vehicleLicenseNumber;
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
