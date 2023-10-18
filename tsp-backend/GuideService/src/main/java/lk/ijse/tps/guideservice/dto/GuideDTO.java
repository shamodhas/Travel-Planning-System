package lk.ijse.tps.guideservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuideDTO {
    private String guideId;
    private String name;
    private String address;
    private String nic;
    private String phone;
    private MultipartFile profile;
    private IdCardDTO idCard;
    private String pricePerDay;
}
