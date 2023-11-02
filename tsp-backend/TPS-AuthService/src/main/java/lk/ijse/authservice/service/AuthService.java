package lk.ijse.authservice.service;

import lk.ijse.authservice.dto.UserDTO;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 5:19 PM
 */

public interface AuthService {
    UserDTO saveUser(UserDTO userDTO);

    String generateToken(String userName);

    void validateToken(String token);
}
