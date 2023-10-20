package lk.ijse.tps.tps.guideservice.util;

import lk.ijse.tps.tps.guideservice.dto.GuideDTO;
import lk.ijse.tps.tps.guideservice.entity.Guide;
import lk.ijse.tps.tps.guideservice.exception.NotFoundException;
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
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public DataTypeConvertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GuideDTO getGuideDTO(Guide guide){
        GuideDTO guideDTO = modelMapper.map(guide, GuideDTO.class);
        try {
            guideDTO.setProfile(decodeImage(guide.getProfile()));
            guideDTO.setIdCardFront(decodeImage(guide.getIdCardFront()));
            guideDTO.setIdCardBack(decodeImage(guide.getIdCardBack()));
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return guideDTO;
    }

    public Guide getGuide(GuideDTO guideDTO){
        Guide guide = modelMapper.map(guideDTO, Guide.class);
        try {
            guide.setProfile(encodeImage(guideDTO.getProfile()));
            guide.setIdCardFront(encodeImage(guideDTO.getIdCardFront()));
            guide.setIdCardBack(encodeImage(guideDTO.getIdCardBack()));
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return guide;
    }

    private String encodeImage(byte[] imageByte) throws IOException {
        return Base64.getEncoder().encodeToString(imageByte);
    }

    private byte[] decodeImage(String imageString) throws IOException {
        return Base64.getDecoder().decode(imageString);
    }
}
