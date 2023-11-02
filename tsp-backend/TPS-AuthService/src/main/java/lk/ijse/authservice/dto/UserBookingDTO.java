package lk.ijse.authservice.dto;

import lk.ijse.authservice.dto.util.Status;
import lk.ijse.authservice.dto.util.VehicleBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/3/2023
 * Time : 1:51 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBookingDTO {
    private String bookingId;
    private String customerId;
    private String packageId;
    private String guideId;
    private String hotelOptionId;
    private List<VehicleBookingDTO> vehicleBookings;
    private int noOfChildren;
    private int noOfAdults;
    private LocalDateTime date;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private BigDecimal downPayment;
}
