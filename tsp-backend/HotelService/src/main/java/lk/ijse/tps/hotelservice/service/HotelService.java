package lk.ijse.tps.hotelservice.service;

import lk.ijse.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.hotelservice.dto.HotelOptionDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:28 AM
 */

public interface HotelService {
    List<HotelDTO> getAllHotel();
    HotelDTO saveHotel(HotelDTO hotelDTO);
    void updateHotel(HotelDTO hotelDTO);
    void deleteHotel(String hotelId);
    HotelDTO getSelectedHotel(String hotelId);

    List<HotelOptionDTO> getAllHotelOption();
    HotelOptionDTO addHotelOption(HotelOptionDTO hotelOptionDTO);
    void updateHotelOption(HotelOptionDTO hotelOptionDTO);
    void deleteHotelOption(String hotelOptionId);
    HotelOptionDTO getSelectedHotelOption(String hotelOptionId);
}
