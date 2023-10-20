package lk.ijse.tps.tps.hotelservice.persistance;

import lk.ijse.tps.tps.hotelservice.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@Repository
public interface HotelDao extends CrudRepository<Hotel, String> {
    List<Hotel> findHotelByCategory(String category);

    Optional<Hotel> findHotelByNameAndAddress(String name, String address);

    List<Hotel> findAll();
}

