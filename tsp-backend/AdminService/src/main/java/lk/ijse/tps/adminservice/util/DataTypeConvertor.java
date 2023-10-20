package lk.ijse.tps.adminservice.util;

import lk.ijse.tps.adminservice.dto.UserDTO;
import lk.ijse.tps.adminservice.entity.User;
import lk.ijse.tps.adminservice.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
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

    public UserDTO getUserDTO(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        try {
            userDTO.setNicFrontImage(decodeImage(user.getNicFrontImage()));
            userDTO.setNicBackImage(decodeImage(user.getNicBackImage()));
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return userDTO;
    }

    public User getUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        try {
            user.setNicFrontImage(encodeImage(userDTO.getNicFrontImage()));
            user.setNicBackImage(encodeImage(userDTO.getNicBackImage()));
            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
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
