package lk.ijse.authservice.dto;

import lk.ijse.authservice.dto.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 2:01 AM
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
    private String username;
    private String password;
    private UserRole userRole;
    // customer
    private byte[] profile;
    // user
    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private String phone;
}
