package lk.ijse.tsp.vehicleservice.service.impl;

import lk.ijse.tsp.vehicleservice.dto.VehicleDTO;
import lk.ijse.tsp.vehicleservice.persistance.VehicleDao;
import lk.ijse.tsp.vehicleservice.service.VehicleService;
import lk.ijse.tsp.vehicleservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
        String vehicleId =null;
        do {
            vehicleId = String.valueOf(UUID.randomUUID());
        } while (vehicleDao.findById(vehicleId).isPresent());
        vehicleDTO.setVehicleId(vehicleId);
        return convertor.getVehicleDTO(vehicleDao.save(convertor.getVehicle(vehicleDTO)));
    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleId) {
        return null;
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {

    }

    @Override
    public void deleteVehicle(String vehicleId) {

    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return null;
    }
}
