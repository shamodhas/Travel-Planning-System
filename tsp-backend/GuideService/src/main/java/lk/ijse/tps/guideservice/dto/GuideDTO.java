package lk.ijse.tps.guideservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuideDTO {
    private String guideId;
    private String name;
    private String address;
    private String nic;
    private String phone;

    private byte[] profile;
    private byte[] idCardFront;
    private byte[] idCardBack;

    private BigDecimal pricePerDay;
}
