package lk.ijse.tps.tps.userservice.persistance;

import lk.ijse.tps.tps.userservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/20/2023
 * Time : 4:46 PM
 */

@Repository
public interface CustomerDao extends CrudRepository<Customer,String> {
    List<Customer> findAll();

    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByNic(String nic);
}
