package lk.ijse.tps.tps.hotelservice.persistance;

import lk.ijse.tps.tps.hotelservice.entity.HotelOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/12/2023
 * Time : 12:10 AM
 */

@Repository
public interface HotelOptionDao extends CrudRepository<HotelOption,String> {
    List<HotelOption> findAll();
}
