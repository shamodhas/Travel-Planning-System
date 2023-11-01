package lk.ijse.authservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 2:01 AM
 */


public class UserDTO {
    private String userId;//
    private String name;//
    private String nic;//
    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private String email;//
    private String phone;
    private String address;//
    private String userName;//
    private String userRole;//
    private String profile;// cus only
    private String password;//
}
