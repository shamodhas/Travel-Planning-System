package lk.ijse.authservice.api;

import lk.ijse.authservice.dto.AuthRequestDTO;
import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.dto.util.UserRole;
import lk.ijse.authservice.exception.InvalidException;
import lk.ijse.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("{userId:^[U][A-Fa-f0-9\\\\-]{36}$}")
    ResponseEntity<?> getSelectedUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getSelectedUser(userId));
    }

    @GetMapping("{userName:^[a-z]{5,15}$}")
    ResponseEntity<?> getSelectedUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getSelectedUserByUserName(userName));
    }

    @GetMapping
    ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequestDTO authRequestDTO) {
        System.out.println(authRequestDTO);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(userService.generateToken(authRequestDTO.getUsername()));
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
            return ResponseEntity.ok(userService.saveUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .username(userName)
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
            return ResponseEntity.ok(userService.saveUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .username(userName)
                            .password(password)
                            .userRole(userRole.equals("USER") ? UserRole.USER : UserRole.ADMIN)

                            .nicFrontImage(nicFrontImage)
                            .nicBackImage(nicBackImage)
                            .phone(phone)
                            .build()
            ));
        } else
            throw new InvalidException("InValid role");
    }

    @PutMapping(value = "{userId:^[U][A-Fa-f0-9\\\\-]{36}$}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> updateUser(
            @PathVariable String userId,
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
            userService.updateUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .username(userName)
                            .password(password)
                            .userRole(UserRole.CUSTOMER)

                            .profile(profile)
                            .build()
            );
            return ResponseEntity.ok("User updated");
        } else if (userRole.equals("USER") || userRole.equals("ADMIN")) {
            if (nicFrontImage.length == 0)
                throw new InvalidException("InValid nic front image");
            if (nicBackImage.length == 0)
                throw new InvalidException("InValid nic back image");
            if (phone == null)
                throw new InvalidException("InValid phone number");
            userService.updateUser(
                    UserDTO.builder()
                            .name(name)
                            .nic(nic)
                            .email(email)
                            .address(address)
                            .username(userName)
                            .password(password)
                            .userRole(userRole.equals("USER") ? UserRole.USER : UserRole.ADMIN)

                            .nicFrontImage(nicFrontImage)
                            .nicBackImage(nicBackImage)
                            .phone(phone)
                            .build()
            );
            return ResponseEntity.ok("User updated");
        } else {
            throw new InvalidException("InValid role");
        }
    }

    @DeleteMapping("{userId:^[U][A-Fa-f0-9\\\\-]{36}$}")
    ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
}
