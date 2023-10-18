package lk.ijse.tps.guideservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/13/2023
 * Time : 10:11 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdCardDTO {
    private MultipartFile front;
    private MultipartFile back;
}
