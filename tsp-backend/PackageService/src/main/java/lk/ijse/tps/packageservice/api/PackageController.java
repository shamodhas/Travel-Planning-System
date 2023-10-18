package lk.ijse.tps.packageservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
        return ResponseEntity.ok(packageService.getSelectedPackage(packageId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(packageService.getAllPackage());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> savePackage(@Valid @RequestBody PackageDTO packageDTO, Errors errors) {
        System.out.println(packageDTO);
        System.out.println(errors);
        return ResponseEntity.ok(packageService.savePackage(packageDTO));
    }

    @PutMapping(value = "{packageId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updatePackage(@PathVariable String packageId, @Valid @RequestBody PackageDTO packageDTO) {
        packageDTO.setPackageId(packageId);
        packageService.updatePackage(packageDTO);
        return ResponseEntity.ok("Package updated");
    }

    @DeleteMapping(value = "{packageId}")
    ResponseEntity<?> deletePackage(@PathVariable String packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.ok("Package deleted");
    }
}
