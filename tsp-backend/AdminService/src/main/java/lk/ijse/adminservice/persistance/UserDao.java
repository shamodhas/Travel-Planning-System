package lk.ijse.adminservice.persistance;

import lk.ijse.adminservice.entity.User;
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
public interface UserDao extends CrudRepository<User,String> {
    Optional<User> findByNic(String nic);
    Optional<User> findByUserName(String userName);

    List<User> findAll();
}
