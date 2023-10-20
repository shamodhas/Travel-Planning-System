package lk.ijse.tps.adminservice.entity;

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

    @Column(nullable = false,columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String nic;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicFrontImage;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicBackImage;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String email;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String phone;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String userName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String userRole;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String password;
}
