package global.govstack.usct.controller;

import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import global.govstack.usct.service.PackageService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@PreAuthorize("hasAnyRole('PAYMENT_OFFICER','ENROLLMENT_OFFICER')")
public class PackageController {

  private final PackageService packageService;

  public PackageController(PackageService packageService) {
    this.packageService = packageService;
  }

  @GetMapping("/packages")
  public List<PackageDto> findById() {
    return packageService.findAll();
  }

  @GetMapping("/packages/{id}")
  public PackageDto findById(@PathVariable("id") int id) {
    return packageService.getById(id);
  }
}
