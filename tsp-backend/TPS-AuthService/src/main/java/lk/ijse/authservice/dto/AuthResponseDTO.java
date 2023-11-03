package lk.ijse.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/4/2023
 * Time : 1:34 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String userId;
    private String username;
    private String userRole;
    private String token;
}
