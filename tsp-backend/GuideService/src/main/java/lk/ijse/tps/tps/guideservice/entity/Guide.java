package lk.ijse.tps.tps.guideservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Guide {
    @Id
    private String guideId;
    private String name;
    private String address;
    private String nic;
    private String phone;

    private String profile;
    private String idCardFront;
    private String idCardBack;

    private BigDecimal pricePerDay;
}
