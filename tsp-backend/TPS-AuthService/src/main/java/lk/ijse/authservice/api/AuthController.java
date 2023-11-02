package lk.ijse.authservice.api;

import jakarta.annotation.security.PermitAll;
import lk.ijse.authservice.dto.AuthRequestDTO;
import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.dto.util.UserRole;
import lk.ijse.authservice.exception.InvalidException;
import lk.ijse.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 2:03 AM
 */

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
    @GetMapping("/get")
    String get(){
        System.out.println("awa");
        return "hari";
    }
//    @PostMapping("/get")
//    String getP(){
//        System.out.println("post");
//        return "hari post";
//    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequestDTO authRequestDTO) {
        System.out.println(authRequestDTO);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequestDTO.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestPart String name,
            @RequestPart String nic,
            @RequestPart String email,
            @RequestPart String address,
            @RequestPart String userName,
            @RequestPart String password,
            @RequestPart String userRole,

            @RequestPart(required = false) byte[] profile,//customer

            @RequestPart(required = false) byte[] nicFrontImage,//admins
            @RequestPart(required = false) byte[] nicBackImage,//admins
            @RequestPart(required = false) String phone//admins
    ) {
        if (userRole == null)
            throw new InvalidException("InValid role");
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (address == null)
            throw new InvalidException("InValid address");
        if (userName == null || !Pattern.matches("^[a-z]{5,15}$", userName))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (password == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password))
            throw new InvalidException("InValid password");

        if (userRole.equals("CUSTOMER")) {
            if (profile.length == 0)
                throw new InvalidException("InValid profile image");
            return ResponseEntity.ok(authService.saveUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .userName(userName)
                            .password(password)
                            .userRole(UserRole.CUSTOMER)

                            .profile(profile)
                            .build()
            ));
        } else if (userRole.equals("USER") || userRole.equals("ADMIN")) {
            if (nicFrontImage.length == 0)
                throw new InvalidException("InValid nic front image");
            if (nicBackImage.length == 0)
                throw new InvalidException("InValid nic back image");
            if (phone == null)
                throw new InvalidException("InValid phone number");
            return ResponseEntity.ok(authService.saveUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .userName(userName)
                            .password(password)
                            .userRole(userRole.equals("USER")?UserRole.USER:UserRole.ADMIN)

                            .nicFrontImage(nicFrontImage)
                            .nicBackImage(nicBackImage)
                            .phone(phone)
                            .build()
            ));
        } else
            throw new InvalidException("InValid role");
    }


}
