package lk.ijse.tps.vehicleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Vehicle {
    @Id
    private String vehicleId;
    private String vehicleLicenseNumber;
    private String brand;
    private String category;
    private String fuelType;
    private boolean isHybrid;
    private double fuelUsagePerKM;

    private String vehicleFrontImage;
    private String vehicleRearImage;
    private String vehicleSideImage;
    private String vehicleFrontInteriorImage;
    private String vehicleRearInteriorImage;

    private BigDecimal pricePerKM;
    private int capacity;
    private String type;
    private String transmission;
    private String driverName;

    private String driverLicenseFrontImage;
    private String driverLicenseBackImage;

    private String phone;
}
