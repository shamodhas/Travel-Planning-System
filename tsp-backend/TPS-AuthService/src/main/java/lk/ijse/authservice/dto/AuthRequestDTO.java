package lk.ijse.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 5:06 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
    private String userRole;
}
