package lk.ijse.tps.hotelservice.dto;

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
    private String hotelOptionId;
    private String hotelId;
    private String type;
    private String capacity;
    private BigDecimal price;
}
