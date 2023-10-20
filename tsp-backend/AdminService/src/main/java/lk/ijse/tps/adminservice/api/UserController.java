package lk.ijse.tps.adminservice.api;

import lk.ijse.tps.adminservice.dto.UserDTO;
import lk.ijse.tps.adminservice.dto.util.UserRole;
import lk.ijse.tps.adminservice.exception.InvalidException;
import lk.ijse.tps.adminservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> verifyUser(
            @RequestPart String userName,
            @RequestPart String password
    ){
        if (userService.authenticateUser(userName,password))
            return ResponseEntity.accepted().body("user verified");
        else
            throw new InvalidException("InValid user");
    }

    @GetMapping
    ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name)) {
            throw new InvalidException("InValid name");
        }
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (nicFrontImage.length == 0)
            throw new InvalidException("InValid nic front image");
        if (nicBackImage.length == 0)
            throw new InvalidException("InValid nic back image");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", phone))
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
                        .password(password)
                        .build()
        ));
    }

    @PutMapping(value = "{userId:^[U][A-Fa-f0-9\\\\-]{36}$}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        if (phone == null || !Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", phone))
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
                        .password(password)
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
