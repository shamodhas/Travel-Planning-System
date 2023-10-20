package lk.ijse.tps.tps.hotelservice.service.impl;

import lk.ijse.tps.tps.hotelservice.dto.HotelDTO;
import lk.ijse.tps.tps.hotelservice.dto.HotelOptionDTO;
import lk.ijse.tps.tps.hotelservice.entity.Hotel;
import lk.ijse.tps.tps.hotelservice.exception.DuplicateException;
import lk.ijse.tps.tps.hotelservice.exception.NotFoundException;
import lk.ijse.tps.tps.hotelservice.persistance.HotelDao;
import lk.ijse.tps.tps.hotelservice.persistance.HotelOptionDao;
import lk.ijse.tps.tps.hotelservice.service.HotelService;
import lk.ijse.tps.tps.hotelservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelOptionDao hotelOptionDao;
    @Autowired
    private DataTypeConvertor convertor;

    @Override
    public List<HotelDTO> getAllHotel() {
        return hotelDao.findAll().stream().map(hotel -> convertor.getHotelDTO(hotel)).collect(Collectors.toList());
    }

    @Override
    public HotelDTO saveHotel(HotelDTO hotelDTO) {
        if (hotelDao.findHotelByNameAndAddress(hotelDTO.getName(), hotelDTO.getAddress()).isPresent())
            throw new DuplicateException("Hotel name and address duplicated");
        String hotelId;
        do {
            hotelId = String.format("HT%S", UUID.randomUUID());
        }while (hotelDao.findById(hotelId).isPresent());
        hotelDTO.setHotelId(hotelId);
        return convertor.getHotelDTO(hotelDao.save(convertor.getHotel(hotelDTO)));
    }

    @Override
    public void updateHotel(HotelDTO hotelDTO) {
        hotelDao.findById(hotelDTO.getHotelId()).orElseThrow(()->new NotFoundException("Hotel not found"));
        Optional<Hotel> optionalHotel = hotelDao.findHotelByNameAndAddress(hotelDTO.getName(), hotelDTO.getAddress());
        if (optionalHotel.isPresent() && !optionalHotel.get().getHotelId().equals(hotelDTO.getHotelId()))
            throw new DuplicateException("Hotel name and address duplicated");
        hotelDao.save(convertor.getHotel(hotelDTO));
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelDao.findById(hotelId).orElseThrow(()->new NotFoundException("Hotel not found"));
        // in use
        hotelDao.deleteById(hotelId);
    }

    @Override
    public HotelDTO getSelectedHotel(String hotelId) {
        return convertor.getHotelDTO(hotelDao.findById(hotelId).orElseThrow(()->new NotFoundException("Hotel not found")));
    }

    @Override
    public HotelOptionDTO addHotelOption(HotelOptionDTO hotelOptionDTO) {
        if (hotelDao.findById(hotelOptionDTO.getHotelId()).isEmpty())
            throw new NotFoundException("Hotel not found for add hotel option");
        String hotelOptionId;
        do {
            hotelOptionId= String.format("HOP%S", UUID.randomUUID());
        }while (hotelOptionDao.findById(hotelOptionId).isPresent());
        hotelOptionDTO.setHotelOptionId(hotelOptionId);
        return convertor.getHotelOptionDTO(hotelOptionDao.save(convertor.getHotelOption(hotelOptionDTO)));
    }

    @Override
    public void updateHotelOption(HotelOptionDTO hotelOptionDTO) {
        hotelOptionDao.findById(hotelOptionDTO.getHotelOptionId()).orElseThrow(()->new NotFoundException("Hotel option not found"));
        if (hotelDao.findById(hotelOptionDTO.getHotelId()).isEmpty())
            throw new NotFoundException("Hotel not found for update hotel option");
        hotelOptionDao.save(convertor.getHotelOption(hotelOptionDTO));
    }

    @Override
    public void deleteHotelOption(String hotelOptionId) {
        hotelOptionDao.findById(hotelOptionId).orElseThrow(()->new NotFoundException("Hotel option not found"));
        // in use
        hotelOptionDao.deleteById(hotelOptionId);
    }

    @Override
    public HotelOptionDTO getSelectedHotelOption(String hotelOptionId) {
        return convertor.getHotelOptionDTO(hotelOptionDao.findById(hotelOptionId).orElseThrow(()->new NotFoundException("Hotel option not found")));
    }

    @Override
    public List<HotelOptionDTO> getAllHotelOption() {
        return hotelOptionDao.findAll().stream().map(hotelOption -> convertor.getHotelOptionDTO(hotelOption)).collect(Collectors.toList());
    }
}
