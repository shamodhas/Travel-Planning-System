package lk.ijse.tps.userservice.api;

import lk.ijse.tps.userservice.dto.CustomerDTO;
import lk.ijse.tps.userservice.exception.InvalidException;
import lk.ijse.tps.userservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/20/2023
 * Time : 4:34 PM
 */

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("{customerId:^[C][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> getSelectedCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getSelectedCustomer(customerId));
    }

    @GetMapping("{userName:^[a-z]{5,15}$}")
    ResponseEntity<?> getSelectedCustomerByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(customerService.getSelectedCustomerByUserName(userName));
    }

    @GetMapping
    ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveCustomer(
            @RequestPart String name,
            @RequestPart String email,
            @RequestPart String nic,
            @RequestPart String address,
            @RequestPart byte[] profile,
            @RequestPart String userName,
            @RequestPart String password
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (address == null)
            throw new InvalidException("InValid address");
        if (profile.length == 0)
            throw new InvalidException("InValid profile image");
        if (userName == null || !Pattern.matches("^[a-z]{5,15}$", userName))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (password == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password))
            throw new InvalidException("InValid password");
        return ResponseEntity.ok(customerService.saveCustomer(
                CustomerDTO.builder()
                        .name(name)
                        .email(email)
                        .nic(nic)
                        .address(address)
                        .profile(profile)
                        .userName(userName)
                        .password(password)
                        .build()
        ));
    }

    @PutMapping(value = "{customerId:^[C][A-Fa-f0-9\\\\-]{36}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateCustomer(
            @PathVariable String customerId,
            @RequestPart String name,
            @RequestPart String email,
            @RequestPart String nic,
            @RequestPart String address,
            @RequestPart byte[] profile,
            @RequestPart String userName,
            @RequestPart String password
    ) {
        if (name == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", name))
            throw new InvalidException("InValid name");
        if (email == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new InvalidException("InValid email");
        if (nic == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", nic))
            throw new InvalidException("InValid nic");
        if (address == null)
            throw new InvalidException("InValid address");
        if (profile.length == 0)
            throw new InvalidException("InValid profile image");
        if (userName == null || !Pattern.matches("^[a-z]{5,15}$", userName))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (password == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password))
            throw new InvalidException("InValid password");
        customerService.updateCustomer(
                CustomerDTO.builder()
                        .customerId(customerId)
                        .name(name)
                        .email(email)
                        .nic(nic)
                        .address(address)
                        .profile(profile)
                        .userName(userName)
                        .password(password)
                        .build()
        );
        return ResponseEntity.ok("Customer updated");
    }

    @DeleteMapping("{customerId:^[C][A-Fa-f0-9\\\\-]{36}}")
    ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted");
    }
}
