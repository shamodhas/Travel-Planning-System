package lk.ijse.adminservice.api;

import lk.ijse.adminservice.dto.UserDTO;
import lk.ijse.adminservice.dto.util.UserRole;
import lk.ijse.adminservice.exception.InvalidException;
import lk.ijse.adminservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:18 AM
 */

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveUser(
            @RequestPart String name,
            @RequestPart String nic,
            @RequestPart byte[] nicFrontImage,
            @RequestPart byte[] nicBackImage,
            @RequestPart String email,
            @RequestPart String phone,
            @RequestPart String address,
            @RequestPart String userName,
            @RequestPart String userRole,
            @RequestPart String password
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (nicFrontImage.length == 0)
            throw new InvalidException("InValid nic front image");
        if (nicBackImage.length == 0)
            throw new InvalidException("InValid nic back image");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (phone == null || !Pattern.matches("", phone))
            throw new InvalidException("InValid phone number");
        if (address == null)
            throw new InvalidException("InValid address");
        if (userName == null || !Pattern.matches("^[a-z]{5,15}$", userName))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (userRole == null || !Pattern.matches("ADMIN||USER",userRole))
            throw new InvalidException("InValid user role");
        if (password == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password))
            throw new InvalidException("InValid password");
        return ResponseEntity.ok(userService.saveUser(
                UserDTO.builder()
                        .name(name)
                        .nic(nic)
                        .nicFrontImage(nicFrontImage)
                        .nicBackImage(nicBackImage)
                        .email(email)
                        .phone(phone)
                        .address(address)
                        .userName(userName)
                        .userRole(UserRole.valueOf(userRole))
                        .build()
        ));
    }

    @PutMapping(value = "{userId:^[U][A-Fa-f0-9\\\\-]{36}$}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateUser(
            @PathVariable String userId,
            @RequestPart String name,
            @RequestPart String nic,
            @RequestPart byte[] nicFrontImage,
            @RequestPart byte[] nicBackImage,
            @RequestPart String email,
            @RequestPart String phone,
            @RequestPart String address,
            @RequestPart String userName,
            @RequestPart String userRole,
            @RequestPart String password
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (nicFrontImage.length == 0)
            throw new InvalidException("InValid nic front image");
        if (nicBackImage.length == 0)
            throw new InvalidException("InValid nic back image");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (phone == null || !Pattern.matches("", phone))
            throw new InvalidException("InValid phone number");
        if (address == null)
            throw new InvalidException("InValid address");
        if (userName == null || !Pattern.matches("^[a-z]{5,15}$", userName))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (userRole == null || !Pattern.matches("ADMIN||USER",userRole))
            throw new InvalidException("InValid user role");
        if (password == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password))
            throw new InvalidException("InValid password");
        userService.updateUser(
                UserDTO.builder()
                        .userId(userId)
                        .name(name)
                        .nic(nic)
                        .nicFrontImage(nicFrontImage)
                        .nicBackImage(nicBackImage)
                        .email(email)
                        .phone(phone)
                        .address(address)
                        .userName(userName)
                        .userRole(UserRole.valueOf(userRole))
                        .build()
        );
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("{userId:^[U][A-Fa-f0-9\\\\-]{36}$}")
    ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
}
