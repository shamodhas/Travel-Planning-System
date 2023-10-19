package lk.ijse.tps.bookingservice.persistance;

import lk.ijse.tps.bookingservice.entity.VehicleBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@Repository
public interface VehicleBookingDao extends CrudRepository<VehicleBooking,Integer> {
    List<VehicleBooking> findAll();
    List<VehicleBooking> findAllByVehicleId(String vehicleId);
}
