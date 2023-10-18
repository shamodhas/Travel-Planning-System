package lk.ijse.tps.bookingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private String bookingId;
    private String customerId;
    private String packageId;
    private String guideId;
    private String hotelOptionId;
    private List<VehicleBookingDTO> vehicleBookings;
    private int noOfChildren;
    private int noOfAdults;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal downPayment;
}
