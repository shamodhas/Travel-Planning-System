package lk.ijse.authservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 11:55 PM
 */
public interface JwtService {
    void validateToken(String token);

    String generateToken(String userName);

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimResolover);

    Boolean validateToken(String token, UserDetails userDetails);
}
