package lk.ijse.tsp.vehicleservice.persistance;

import lk.ijse.tsp.vehicleservice.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:52 AM
 */

@Repository
public interface VehicleDao extends CrudRepository<Vehicle, String> {
    List<Vehicle> findAll();
}
