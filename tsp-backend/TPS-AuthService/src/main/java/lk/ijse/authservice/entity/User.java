package lk.ijse.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 1:52 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String userId;//

    @Column(columnDefinition = "TEXT")
    private String name;//

    @Column(columnDefinition = "TEXT")
    private String nic;//

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String nicFrontImage;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String nicBackImage;

    @Column(columnDefinition = "TEXT")
    private String email;//

    @Column(columnDefinition = "TEXT")
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;//

    @Column(columnDefinition = "TEXT")
    private String userName;//

    @Column(columnDefinition = "TEXT")
    private String userRole;//

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String profile;// cus only

    @Column(columnDefinition = "LONGTEXT")
    private String password;//
}
