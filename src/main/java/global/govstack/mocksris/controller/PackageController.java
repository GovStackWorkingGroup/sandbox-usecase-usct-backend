package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.PackageDto;
import global.govstack.mocksris.service.PackageService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class PackageController {

  private final PackageService packageService;

  public PackageController(PackageService packageService) {
    this.packageService = packageService;
  }

  @GetMapping("/packages")
  public List<PackageDto> findById() {
    return packageService.findAll().stream().map(PackageDto::new).toList();
  }

  @GetMapping("/packages/{id}")
  public PackageDto findById(@PathVariable("id") int id) {
    return new PackageDto(packageService.getById(id));
  }
}
