package lk.ijse.tps.packageservice.api;

import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 10/9/2023
 * Time : 12:44 AM
 */

@RestController
@RequestMapping("api/v1/package")
@CrossOrigin("*")
public class PackageController {
    @Autowired
    PackageService packageService;

    @GetMapping(value = "{packageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSelectedPackageById(@PathVariable String packageId) {
//        return new ResponseEntity<>(packageService.getSelectedPackage(packageId), HttpStatus.OK);
        return ResponseEntity.ok(packageService.getSelectedPackage(packageId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> savePackage(PackageDTO packageDTO) {
        return new ResponseEntity<>(packageService.savePackage(packageDTO),HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<?> updatePackage(String packageId,PackageDTO packageDTO){
        return null;
    }

    @DeleteMapping
    ResponseEntity<?> deletePackage(String packageId){
        return null;
    }
}
