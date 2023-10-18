package lk.ijse.tps.packageservice.service.impl;

import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.exception.NotFoundException;
import lk.ijse.tps.packageservice.persistance.PackageDao;
import lk.ijse.tps.packageservice.service.PackageService;
import lk.ijse.tps.packageservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:55 AM
 */

@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageDao packageDao;
    @Autowired
    private DataTypeConvertor convertor;

    @Override
    public PackageDTO savePackage(PackageDTO packageDTO) {
        packageDTO.setPackageId(UUID.randomUUID().toString());
        System.out.println(packageDTO.getPackageId());
        return convertor.getPackageDTO(packageDao.save(convertor.getPackage(packageDTO)));
    }

    @Override
    public PackageDTO getSelectedPackage(String packageId) {
        return convertor.getPackageDTO(packageDao.findById(packageId).orElseThrow(() -> new NotFoundException("package not found..!")));
    }

    @Override
    public void updatePackage(PackageDTO packageDTO) {

    }

    @Override
    public void deletePackage(String packageId) {

    }

    @Override
    public List<PackageDTO> getAllPackage() {
        return packageDao.findAll().stream().map(p -> convertor.getPackageDTO(p)).collect(Collectors.toList());
    }
}
