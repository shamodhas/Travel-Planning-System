package lk.ijse.tps.hotelservice.persistance;

import lk.ijse.tps.hotelservice.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@Repository
public interface HotelDao extends CrudRepository<Hotel, String> {
    List<Hotel> findHotelByCategory(String category);
    List<Hotel> findAll();
}

