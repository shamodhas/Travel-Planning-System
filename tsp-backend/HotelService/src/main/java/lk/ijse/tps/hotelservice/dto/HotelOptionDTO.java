package lk.ijse.tps.hotelservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 2:30 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelOptionDTO {
    @Null(message = "Hotel option id auto generated")
    private String hotelOptionId;

    @NotNull(message = "InValid hotelId")
    private String hotelId;

    @NotNull(message = "InValid type")
    private String type;

    @NotNull(message = "InValid capacity")
    private String capacity;

    @NotNull(message = "InValid price")
    @DecimalMin(value = "0.00", message = "InValid price")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "InValid price")
    private BigDecimal price;
}
