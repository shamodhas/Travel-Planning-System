package lk.ijse.tps.adminservice.dto;

import lk.ijse.tps.adminservice.dto.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 5:36 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String userId;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String userName;
    private String password;
    private UserRole userRole;

    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private String phone;
}
