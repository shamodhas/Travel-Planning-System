package lk.ijse.tps.packageservice.util;

import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.entity.Package;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
    public Package getPackage(PackageDTO packageDTO){
        return modelMapper.map(packageDTO, Package.class);
    }
    public PackageDTO getPackageDTO(Package aPackage){
        return modelMapper.map(aPackage, PackageDTO.class);
    }
}
