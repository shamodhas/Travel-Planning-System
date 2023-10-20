package lk.ijse.tps.adminservice.service.impl;

import lk.ijse.tps.adminservice.dto.UserDTO;
import lk.ijse.tps.adminservice.entity.User;
import lk.ijse.tps.adminservice.exception.DuplicateException;
import lk.ijse.tps.adminservice.exception.NotFoundException;
import lk.ijse.tps.adminservice.persistance.UserDao;
import lk.ijse.tps.adminservice.service.UserService;
import lk.ijse.tps.adminservice.util.DataTypeConvertor;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:55 AM
 */

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final DataTypeConvertor convertor;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDao.findByNic(userDTO.getNic()).isPresent())
            throw new DuplicateException("User nic duplicated");
        if (userDao.findByUserName(userDTO.getUserName()).isPresent())
            throw new DuplicateException("User userName duplicated");
        String userId;
        do {
            userId = String.format("U%S", UUID.randomUUID());
        } while (userDao.findById(userId).isPresent());
        userDTO.setUserId(userId);
        return convertor.getUserDTO(userDao.save(convertor.getUser(userDTO)));
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return convertor.getUserDTO(userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public UserDTO getSelectedUserByUserName(String userName) {
        return convertor.getUserDTO(userDao.findByUserName(userName).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userDao.findById(userDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Optional<User> optionalUserForNic = userDao.findByNic(userDTO.getNic());
        if (optionalUserForNic.isPresent() && !optionalUserForNic.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate user nic");
        Optional<User> optionalUserForUserName = userDao.findByUserName(userDTO.getUserName());
        if (optionalUserForUserName.isPresent() && !optionalUserForUserName.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate userName");
        userDao.save(convertor.getUser(userDTO));
    }

    @Override
    public void deleteUser(String userId) {
        userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        userDao.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userDao.findAll().stream().map(convertor::getUserDTO).collect(Collectors.toList());
    }

    @Override
    public boolean verifyUser(String userName, String password) {
        User user = userDao.findByUserName(userName).orElseThrow(() -> new NotFoundException("User not found"));
        return BCrypt.checkpw(password, user.getPassword());
    }
}
