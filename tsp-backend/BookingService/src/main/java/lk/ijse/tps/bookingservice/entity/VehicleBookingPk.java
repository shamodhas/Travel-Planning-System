package lk.ijse.tps.bookingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/13/2023
 * Time : 9:55 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleBookingPk implements Serializable {
    private String bookingId;
    private String vehicleId;
}
