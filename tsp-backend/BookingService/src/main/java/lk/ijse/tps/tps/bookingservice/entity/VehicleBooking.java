package lk.ijse.tps.tps.bookingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/13/2023
 * Time : 9:54 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(VehicleBookingPk.class)
public class VehicleBooking {
    @Id
    private String bookingId;
    @Id
    private String vehicleId;

    private double distance;
    private BigDecimal pricePerKM;
    private int rate;

    @ManyToOne
    @JoinColumn(name = "bookingId",referencedColumnName = "bookingId",insertable = false,updatable = false)
    private Booking booking;
}
