package lk.ijse.authservice.util;


import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.dto.util.UserRole;
import lk.ijse.authservice.entity.User;
import lk.ijse.authservice.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 1:00 AM
 */

@Component
@RequiredArgsConstructor
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        try {
            if (user.getUserRole().equalsIgnoreCase("CUSTOMER")) {

            } else if (user.getUserRole().equalsIgnoreCase("USER") || user.getUserRole().equalsIgnoreCase("ADMIN")) {
                userDTO.setNicFrontImage(decodeImage(user.getNicFrontImage()));
                userDTO.setNicBackImage(decodeImage(user.getNicBackImage()));
            }
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return userDTO;
    }

    public User getUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        try {
            if (userDTO.getUserRole().equals(UserRole.CUSTOMER)) {

            } else if (userDTO.getUserRole().equals(UserRole.USER) || userDTO.getUserRole().equals(UserRole.ADMIN)) {
                user.setNicFrontImage(encodeImage(userDTO.getNicFrontImage()));
                user.setNicBackImage(encodeImage(userDTO.getNicBackImage()));
            }
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return user;
    }

    private String encodeImage(byte[] imageByte) throws IOException {
        return Base64.getEncoder().encodeToString(imageByte);
    }

    private byte[] decodeImage(String imageString) throws IOException {
        return Base64.getDecoder().decode(imageString);
    }
}
