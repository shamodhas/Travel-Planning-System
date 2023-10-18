package lk.ijse.tps.hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lk.ijse.tps.hotelservice.dto.HotelOptionDTO;
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
@Entity
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String category;
    private String address;
    private String location;
    private String email;
    private String phone;
    private boolean isPetAllowed;
    private String cancelCriteria;

    @OneToMany(mappedBy = "hotel")
    private List<HotelOption> hotelOptions;
}
