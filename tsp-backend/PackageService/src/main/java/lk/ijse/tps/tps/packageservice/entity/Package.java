package lk.ijse.tps.tps.packageservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:46 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Package {
    @Id
    private String packageId;
    private String category;
    private String area;
    private BigDecimal price;
    private int averageNoOfDate;
}
