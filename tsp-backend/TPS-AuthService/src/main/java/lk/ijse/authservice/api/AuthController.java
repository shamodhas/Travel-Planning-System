package lk.ijse.authservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 2:03 AM
 */

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(){
        return null;
    }
}
