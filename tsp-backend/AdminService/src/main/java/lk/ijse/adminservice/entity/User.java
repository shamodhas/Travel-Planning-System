package lk.ijse.adminservice.entity;

import jakarta.persistence.*;
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
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String nic;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicFrontImage;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicBackImage;

    private String email;
    private String phone;
    private String address;
    private String userName;
    private String userRole;
    private String password;
}
