package lk.ijse.tps.adminservice.service;


import lk.ijse.tps.adminservice.dto.UserDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:28 AM
 */

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getSelectedUser(String userId);
    UserDTO getSelectedUserByUserName(String userName);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    List<UserDTO> getAllUser();
    boolean authenticateUser(String userName, String password);
}
