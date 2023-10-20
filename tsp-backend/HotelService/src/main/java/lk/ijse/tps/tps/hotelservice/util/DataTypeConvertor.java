package lk.ijse.tps.tps.hotelservice.util;

import lk.ijse.tps.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.tps.hotelservice.dto.HotelOptionDTO;
import lk.ijse.tps.tps.hotelservice.entity.Hotel;
import lk.ijse.tps.tps.hotelservice.entity.HotelOption;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

    public HotelDTO getHotelDTO(Hotel hotel){
        return modelMapper.map(hotel,HotelDTO.class);
    }

    public Hotel getHotel(HotelDTO hotelDTO){
        return modelMapper.map(hotelDTO,Hotel.class);
    }

    public HotelOptionDTO getHotelOptionDTO(HotelOption hotelOption){
        return modelMapper.map(hotelOption,HotelOptionDTO.class);
    }

    public HotelOption getHotelOption(HotelOptionDTO hotelOptionDTO){
        return modelMapper.map(hotelOptionDTO,HotelOption.class);
    }
}
