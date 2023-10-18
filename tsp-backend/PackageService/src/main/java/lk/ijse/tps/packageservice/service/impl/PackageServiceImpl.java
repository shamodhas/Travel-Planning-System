package lk.ijse.tps.packageservice.service.impl;

import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.entity.Package;
import lk.ijse.tps.packageservice.exception.DuplicateException;
import lk.ijse.tps.packageservice.exception.NotFoundException;
import lk.ijse.tps.packageservice.persistance.PackageDao;
import lk.ijse.tps.packageservice.service.PackageService;
import lk.ijse.tps.packageservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        if (packageDao.findByCategoryAndArea(packageDTO.getCategory(),packageDTO.getArea()).isPresent())
            throw new DuplicateException("Duplicate category and area");
        String packageId;
        do {
            packageId = String.valueOf(UUID.randomUUID());
        } while (packageDao.findById(packageId).isPresent());
        packageDTO.setPackageId(packageId);
        return convertor.getPackageDTO(packageDao.save(convertor.getPackage(packageDTO)));
    }

    @Override
    public PackageDTO getSelectedPackage(String packageId) {
        return convertor.getPackageDTO(packageDao.findById(packageId).orElseThrow(() -> new NotFoundException("Package not found")));
    }

    @Override
    public void updatePackage(PackageDTO packageDTO) {
        packageDao.findById(packageDTO.getPackageId()).orElseThrow(()->new NotFoundException("Package not found"));
        Optional<Package> aPackage = packageDao.findByCategoryAndArea(packageDTO.getCategory(), packageDTO.getArea());
        if (aPackage.isPresent() && !aPackage.get().getPackageId().equals(packageDTO.getPackageId()))
            throw new DuplicateException("Duplicate category and area");
        packageDao.save(convertor.getPackage(packageDTO));
    }

    @Override
    public void deletePackage(String packageId) {
        packageDao.findById(packageId).orElseThrow(()->new NotFoundException("Package not found"));
        // in use
        packageDao.deleteById(packageId);
    }

    @Override
    public List<PackageDTO> getAllPackage() {
        return packageDao.findAll().stream().map(aPackage -> convertor.getPackageDTO(aPackage)).collect(Collectors.toList());
    }
}
