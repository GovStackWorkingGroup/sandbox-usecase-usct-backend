package global.govstack.mocksris.controller;

import global.govstack.mocksris.service.DigitalRegistriesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/digitalregistries")
@CrossOrigin
@PreAuthorize("hasAnyRole('PAYMENT_OFFICER','ENROLLMENT_OFFICER','REGISTRY_OFFICER')")
public class DigitalRegistriesRestController {
  private final DigitalRegistriesService digitalRegistriesService;

  public DigitalRegistriesRestController(DigitalRegistriesService digitalRegistriesService) {
    this.digitalRegistriesService = digitalRegistriesService;
  }

  @GetMapping("/emulator-health")
  public String getHealth() {
    return digitalRegistriesService.health();
  }
}
