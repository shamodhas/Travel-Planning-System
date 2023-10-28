package lk.ijse.tps.tps.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
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
    @Null(message = "Hotel id auto generated")
    private String hotelId;

    @NotNull(message = "InValid name")
    private String name;

    @NotNull(message = "InValid category")
    private String category;

    @NotNull(message = "InValid address")
    private String address;

    @NotNull(message = "InValid location")
    private String location;

    @NotNull(message = "InValid email")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "InValid email")
    private String email;

    @NotNull(message = "InValid phone")
    @Pattern(regexp = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", message = "InValid phone number")
    private String phone;

    @JsonProperty
    private boolean isPetAllowed;

    @NotNull(message = "InValid cancelCriteria")
    private String cancelCriteria;

    private List<HotelOptionDTO> hotelOptions;
}
