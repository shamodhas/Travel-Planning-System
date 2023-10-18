package lk.ijse.tps.packageservice.persistance;

import lk.ijse.tps.packageservice.entity.Package;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:52 AM
 */

@Repository
public interface PackageDao extends CrudRepository<Package, String> {
    List<Package> findAll();
}
