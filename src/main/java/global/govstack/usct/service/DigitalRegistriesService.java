package global.govstack.usct.service;

import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import java.util.List;

public interface DigitalRegistriesService {

  List<PackageDto> getAll();
}
