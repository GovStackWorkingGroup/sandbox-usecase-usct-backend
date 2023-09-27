package global.govstack.mocksris.controller;

import global.govstack.mocksris.service.DigitalRegistriesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/digitalregistries")
@CrossOrigin
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
