package lk.ijse.tsp.vehicleservice.util;

import lk.ijse.tsp.vehicleservice.dto.VehicleDTO;
import lk.ijse.tsp.vehicleservice.entity.Vehicle;
import lk.ijse.tsp.vehicleservice.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 1:00 AM
 */

@Component
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public DataTypeConvertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VehicleDTO getVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
        try {
            vehicleDTO.setVehicleFrontImage(decodeImage(vehicle.getVehicleFrontImage()));
            vehicleDTO.setVehicleRearImage(decodeImage(vehicle.getVehicleRearImage()));
            vehicleDTO.setVehicleSideImage(decodeImage(vehicle.getVehicleSideImage()));
            vehicleDTO.setVehicleFrontInteriorImage(decodeImage(vehicle.getVehicleFrontInteriorImage()));
            vehicleDTO.setVehicleRearInteriorImage(decodeImage(vehicle.getVehicleRearInteriorImage()));
            vehicleDTO.setDriverLicenseFrontImage(decodeImage(vehicle.getDriverLicenseFrontImage()));
            vehicleDTO.setDriverLicenseBackImage(decodeImage(vehicle.getDriverLicenseBackImage()));
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return vehicleDTO;
    }

    public Vehicle getVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        try {
            vehicle.setVehicleFrontImage(encodeImage(vehicleDTO.getVehicleFrontImage()));
            vehicle.setVehicleRearImage(encodeImage(vehicleDTO.getVehicleRearImage()));
            vehicle.setVehicleSideImage(encodeImage(vehicleDTO.getVehicleSideImage()));
            vehicle.setVehicleFrontInteriorImage(encodeImage(vehicleDTO.getVehicleFrontInteriorImage()));
            vehicle.setVehicleRearInteriorImage(encodeImage(vehicleDTO.getVehicleRearInteriorImage()));
            vehicle.setDriverLicenseFrontImage(encodeImage(vehicleDTO.getDriverLicenseFrontImage()));
            vehicle.setDriverLicenseBackImage(encodeImage(vehicleDTO.getDriverLicenseBackImage()));
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return vehicle;
    }

    private String encodeImage(byte[] imageByte) throws IOException {
        return Base64.getEncoder().encodeToString(imageByte);
    }

    private byte[] decodeImage(String imageString) throws IOException {
        return Base64.getDecoder().decode(imageString);
    }
}
