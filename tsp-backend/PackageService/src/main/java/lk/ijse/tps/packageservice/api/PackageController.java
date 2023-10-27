package lk.ijse.tps.packageservice.api;

import jakarta.validation.Valid;
import lk.ijse.tps.packageservice.dto.PackageDTO;
import lk.ijse.tps.packageservice.service.PackageService;
import lk.ijse.tps.packageservice.exception.InvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

    @GetMapping(value = "{packageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSelectedPackageById(@PathVariable String packageId) {
        return ResponseEntity.ok(packageService.getSelectedPackage(packageId));
    }

    @GetMapping(value = "/public")
    ResponseEntity<?> getAll() {
        System.out.println("awa");
        return ResponseEntity.ok(packageService.getAllPackage());
    }

    @PostMapping(value = "/public",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> savePackage(@Valid @RequestBody PackageDTO packageDTO, Errors errors) {
        System.out.println(packageDTO);
        if (errors.hasErrors()) {
            System.out.println(errors);
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        return ResponseEntity.ok(packageService.savePackage(packageDTO));
    }

    @PutMapping(value = "{packageId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updatePackage(@PathVariable String packageId, @Valid @RequestBody PackageDTO packageDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
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
