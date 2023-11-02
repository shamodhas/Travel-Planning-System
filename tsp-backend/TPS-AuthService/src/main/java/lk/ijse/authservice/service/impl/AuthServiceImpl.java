package lk.ijse.authservice.service.impl;

import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.dto.util.UserRole;
import lk.ijse.authservice.exception.DuplicateException;
import lk.ijse.authservice.persistance.UserDao;
import lk.ijse.authservice.service.AuthService;
import lk.ijse.authservice.service.JwtService;
import lk.ijse.authservice.util.DataTypeConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 5:19 PM
 */

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final DataTypeConvertor convertor;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDao.findByNic(userDTO.getNic()).isPresent())
            throw new DuplicateException("Nic duplicated");
        if (userDao.findByUserName(userDTO.getUserName()).isPresent())
            throw new DuplicateException("userName duplicated");
        if (userDTO.getUserRole().equals(UserRole.CUSTOMER)) {
            String userId;
            do {
                userId = String.format("C%S", UUID.randomUUID());
            } while (userDao.findById(userId).isPresent());
            userDTO.setUserId(userId);
        } else if (userDTO.getUserRole().equals(UserRole.USER) || userDTO.getUserRole().equals(UserRole.ADMIN)) {
            String userId;
            do {
                userId = String.format("U%S", UUID.randomUUID());
            } while (userDao.findById(userId).isPresent());
            userDTO.setUserId(userId);
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return convertor.getUserDTO(userDao.save(convertor.getUser(userDTO)));
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
