package lk.ijse.tps.tps.bookingservice.util;

import lk.ijse.tps.tps.bookingservice.dto.BookingDTO;
import lk.ijse.tps.tps.bookingservice.dto.VehicleBookingDTO;
import lk.ijse.tps.tps.bookingservice.entity.Booking;
import lk.ijse.tps.tps.bookingservice.entity.VehicleBooking;
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

    public Booking getBooking(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO, Booking.class);
    }

    public BookingDTO getBookingDTO(Booking booking) {
        return modelMapper.map(booking, BookingDTO.class);
    }

    public VehicleBooking getVehicleBooking(VehicleBookingDTO vehicleBookingDTO) {
        return modelMapper.map(vehicleBookingDTO, VehicleBooking.class);
    }

    public VehicleBookingDTO getVehicleBookingDTO(VehicleBooking vehicleBooking) {
        return modelMapper.map(vehicleBooking, VehicleBookingDTO.class);
    }
}
