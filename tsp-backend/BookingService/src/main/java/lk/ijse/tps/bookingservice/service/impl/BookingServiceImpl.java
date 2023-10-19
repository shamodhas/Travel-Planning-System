package lk.ijse.tps.bookingservice.service.impl;

import lk.ijse.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.bookingservice.entity.Booking;
import lk.ijse.tps.bookingservice.exception.InUseException;
import lk.ijse.tps.bookingservice.exception.NotFoundException;
import lk.ijse.tps.bookingservice.persistance.BookingDao;
import lk.ijse.tps.bookingservice.persistance.VehicleBookingDao;
import lk.ijse.tps.bookingservice.service.BookingService;
import lk.ijse.tps.bookingservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        String bookingId;
        do {
            bookingId = String.valueOf(UUID.randomUUID());
        }while (bookingDao.existsById(bookingId));
        // call customer service findById() not null
        // call package service findById() not null
        // call guide service findById() guide id can null
        // call hotel option service findById() can null
        // call vehicle service findById() list can null
        return convertor.getBookingDTO(bookingDao.save(convertor.getBooking(bookingDTO)));
    }

    @Override
    public BookingDTO getSelectedBooking(String bookingId) {
        return convertor.getBookingDTO(bookingDao.findById(bookingId).orElseThrow(()->new NotFoundException("Booking not found")));
    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {
        bookingDao.findById(bookingDTO.getBookingId()).orElseThrow(()->new NotFoundException("Booking not found"));
        // call customer service findById() not null
        // call package service findById() not null
        // call guide service findById() guide id can null
        // call hotel option service findById() can null
        // call vehicle service findById() list can null
        bookingDao.save(convertor.getBooking(bookingDTO));
    }

    @Override
    public void deleteBooking(String bookingId) {
        Booking booking = bookingDao.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));
        if (booking.getStatus().equals("ACTIVE"))
            throw new InUseException("Booking is active");
        bookingDao.deleteById(bookingId);

    }

    @Override
    public List<BookingDTO> getAllBooking() {
        return bookingDao.findAll().stream().map(booking -> convertor.getBookingDTO(booking)).collect(Collectors.toList());
    }
}
