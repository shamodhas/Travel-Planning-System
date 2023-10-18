package lk.ijse.tps.guideservice.persistance;

import lk.ijse.tps.guideservice.entity.Guide;
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
public interface GuideDao extends CrudRepository<Guide,String> {
    Optional<Guide> findGuideByNic(String nic);
    List<Guide> findAll();
}
