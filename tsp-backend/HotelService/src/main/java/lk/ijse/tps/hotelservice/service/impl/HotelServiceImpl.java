package lk.ijse.tps.hotelservice.service.impl;

import lk.ijse.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.hotelservice.dto.HotelOptionDTO;
import lk.ijse.tps.hotelservice.persistance.HotelDao;
import lk.ijse.tps.hotelservice.persistance.HotelOptionDao;
import lk.ijse.tps.hotelservice.service.HotelService;
import lk.ijse.tps.hotelservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:55 AM
 */

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelOptionDao hotelOptionDao;
    @Autowired
    private DataTypeConvertor convertor;

    @Override
    public List<HotelDTO> getAllHotel() {
        return null;
    }

    @Override
    public HotelDTO saveHotel(HotelDTO hotelDTO) {
        return null;
    }

    @Override
    public void updateHotel(HotelDTO hotelDTO) {

    }

    @Override
    public void deleteHotel(String hotelId) {

    }

    @Override
    public HotelDTO getSelectedHotel(String hotelId) {
        return null;
    }

    @Override
    public HotelOptionDTO addHotelOption(HotelOptionDTO hotelOptionDTO) {
        return null;
    }

    @Override
    public void updateHotelOption(HotelOptionDTO hotelOptionDTO) {

    }

    @Override
    public void deleteHotelOption(String hotelOptionId) {

    }

    @Override
    public List<HotelOptionDTO> getHotelOptionByHotelId(String hotelId) {
        return null;
    }
}
