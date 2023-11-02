package lk.ijse.authservice.persistance;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import lk.ijse.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 1:59 AM
 */
public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findByUserName(String username);

    Optional<User> findByNic(String nic);
}
