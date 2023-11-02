package lk.ijse.authservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 1:52 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements UserDetails {

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

    public User(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define the authorities (roles) for the user here if needed.
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
