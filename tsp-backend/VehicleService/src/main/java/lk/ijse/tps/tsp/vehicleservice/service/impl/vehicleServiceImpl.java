package lk.ijse.tps.tsp.vehicleservice.service.impl;

import lk.ijse.tps.tsp.vehicleservice.exception.DuplicateException;
import lk.ijse.tps.tsp.vehicleservice.exception.NotFoundException;
import lk.ijse.tps.tsp.vehicleservice.persistance.VehicleDao;
import lk.ijse.tps.tsp.vehicleservice.dto.VehicleDTO;
import lk.ijse.tps.tsp.vehicleservice.entity.Vehicle;
import lk.ijse.tps.tsp.vehicleservice.service.VehicleService;
import lk.ijse.tps.tsp.vehicleservice.util.DataTypeConvertor;
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
public class vehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DataTypeConvertor convertor;

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleDao.findVehicleByVehicleLicenseNumber(vehicleDTO.getVehicleLicenseNumber()).isPresent())
            throw new DuplicateException("Vehicle license number duplicated");
        String vehicleId;
        do {
            vehicleId = String.format("V%S", UUID.randomUUID());
        } while (vehicleDao.findById(vehicleId).isPresent());
        vehicleDTO.setVehicleId(vehicleId);
        return convertor.getVehicleDTO(vehicleDao.save(convertor.getVehicle(vehicleDTO)));
    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleId) {
        return convertor.getVehicleDTO(vehicleDao.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicle not found")));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        vehicleDao.findById(vehicleDTO.getVehicleId()).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        Optional<Vehicle> optionalVehicle = vehicleDao.findVehicleByVehicleLicenseNumber(vehicleDTO.getVehicleLicenseNumber());
        if (optionalVehicle.isPresent() && !optionalVehicle.get().getVehicleId().equals(vehicleDTO.getVehicleId()))
            throw new DuplicateException("Duplicate license number");
        vehicleDao.save(convertor.getVehicle(vehicleDTO));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        vehicleDao.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        // in use check
        vehicleDao.deleteById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return vehicleDao.findAll().stream().map(vehicle -> convertor.getVehicleDTO(vehicle)).collect(Collectors.toList());
    }
}
