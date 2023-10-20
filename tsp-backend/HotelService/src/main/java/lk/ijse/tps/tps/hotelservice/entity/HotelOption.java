package lk.ijse.tps.tps.hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
public class HotelOption {
    @Id
    private String hotelOptionId;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

    private String type;
    private String capacity;
    private BigDecimal price;
}
