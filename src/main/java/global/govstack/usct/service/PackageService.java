package global.govstack.usct.service;

import global.govstack.usct.controller.dto.PackageDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
  private final OpenImisService openImisService;
  private Map<Integer, PackageDto> packagesCache;

  public PackageService(OpenImisService openImisService) {
    this.openImisService = openImisService;
    this.packagesCache = new ConcurrentHashMap<>();
  }

  public List<PackageDto> findAll() {
    if (packagesCache.isEmpty()) {
      List<PackageDto> allPackages = openImisService.getAll();
      allPackages.forEach(item -> packagesCache.put(item.getId(), item));
      return allPackages;
    }
    return new ArrayList<PackageDto>(packagesCache.values());
  }

  public PackageDto getById(int id) {
    if (packagesCache.isEmpty()) {
      List<PackageDto> allPackages = openImisService.getAll();
      allPackages.forEach(item -> packagesCache.put(item.getId(), item));
    }
    return packagesCache.get(id);
  }
}
