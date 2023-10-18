package lk.ijse.tps.bookingservice.service;


import lk.ijse.tps.bookingservice.dto.BookingDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 1:28 AM
 */

public interface BookingService {
    BookingDTO addBooking(BookingDTO bookingDTO);
    BookingDTO getSelectedBooking(String bookingId);
    void updateBooking(BookingDTO bookingDTO);
    void deleteBooking(String bookingId);
    List<BookingDTO> getAllBooking();
}
