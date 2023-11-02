package lk.ijse.tps.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class CustomerDTO {
    private String customerId;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String userName;
    private String password;
//    private UserRole userRole;

    private byte[] profile;
}
