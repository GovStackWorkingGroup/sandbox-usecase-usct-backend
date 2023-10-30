package global.govstack.mocksris.service;

import java.util.List;

import global.govstack.mocksris.controller.dto.PackageDto;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
  private final OpenImisService openImisService;

  public PackageService(OpenImisService openImisService) {
    this.openImisService = openImisService;
  }

  public List<PackageDto> findAll() {
    return openImisService.getAll();
  }

  public PackageDto getById(int id) {
    return openImisService
        .getById(id)
        .orElseThrow(() -> new RuntimeException("Package with id: " + id + " doesn't exist"));
  }
}
