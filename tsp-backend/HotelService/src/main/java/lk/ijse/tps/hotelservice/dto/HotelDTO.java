package lk.ijse.tps.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {
    private String hotelId;
    private String name;
    private String category;
    private String address;
    private String location;
    private String email;
    private String phone;
    private boolean isPetAllowed;
    private String cancelCriteria;
    private List<HotelOptionDTO> hotelOptions;
}
