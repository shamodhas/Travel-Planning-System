package lk.ijse.authservice.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/3/2023
 * Time : 1:53 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleBookingDTO {
    private String bookingId;
    private String vehicleId;
    private double distance;
    private BigDecimal pricePerKM;
    private int rate;
}
