package lk.ijse.tps.userservice.dto;

import lombok.AllArgsConstructor;
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
public class UserDTO {
    private String userId;
    private String name;
    private String nic;
    private String email;
    private String phone;
    private String address;
    private String userName;
    private String userRole;
    private String password;
}
