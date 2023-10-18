package lk.ijse.tps.packageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:45 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDTO {
    private String packageId;
    private String category;
    private String area;
    private BigDecimal price;
    private int averageNoOfDate;
}
