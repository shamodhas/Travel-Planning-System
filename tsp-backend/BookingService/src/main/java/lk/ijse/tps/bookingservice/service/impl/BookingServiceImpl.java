package lk.ijse.tps.bookingservice.service.impl;

import lk.ijse.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.bookingservice.persistance.BookingDao;
import lk.ijse.tps.bookingservice.persistance.VehicleBookingDao;
import lk.ijse.tps.bookingservice.service.BookingService;
import lk.ijse.tps.bookingservice.util.DataTypeConvertor;
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
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private VehicleBookingDao vehicleBookingDao;
    @Autowired
    private DataTypeConvertor convertor;
    @Override
    public BookingDTO addBooking(BookingDTO bookingDTO) {
        return null;
    }

    @Override
    public BookingDTO getSelectedBooking(String bookingId) {
        return null;
    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {

    }

    @Override
    public void deleteBooking(String bookingId) {

    }

    @Override
    public List<BookingDTO> getAllBooking() {
        return null;
    }
}
