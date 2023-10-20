package lk.ijse.tps.vehicleservice.service;

import lk.ijse.tps.vehicleservice.dto.VehicleDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:54 AM
 */

public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getSelectedVehicle(String vehicleId);
    void updateVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleId);
    List<VehicleDTO> getAllVehicle();
}
