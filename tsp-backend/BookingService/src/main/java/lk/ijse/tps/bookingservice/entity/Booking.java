package lk.ijse.tps.bookingservice.entity;

import jakarta.persistence.*;
import lk.ijse.tps.bookingservice.dto.VehicleBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/13/2023
 * Time : 9:53 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {
    @Id
    private String bookingId;
    private String customerId;
    private String packageId;
    private String guideId;
    private String hotelOptionId;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<VehicleBooking> vehicleBookings;

    private int noOfChildren;
    private int noOfAdults;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal downPayment;
}
