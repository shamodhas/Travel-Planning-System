package lk.ijse.tps.tps.packageservice.service;

import lk.ijse.tps.tps.packageservice.dto.PackageDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:54 AM
 */

public interface PackageService {
    PackageDTO savePackage(PackageDTO packageDTO);
    PackageDTO getSelectedPackage(String packageId);
    void updatePackage(PackageDTO packageDTO);
    void deletePackage(String packageId);
    List<PackageDTO> getAllPackage();
}
