package global.govstack.usct.service;

import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
  private final DigitalRegistriesService digitalRegistries;
  private Map<Integer, PackageDto> packagesCache;

  public PackageService(DigitalRegistriesService digitalRegistries) {
    this.digitalRegistries = digitalRegistries;
    this.packagesCache = new ConcurrentHashMap<>();
  }

  public List<PackageDto> findAll() {
    if (packagesCache.isEmpty()) {
      List<PackageDto> allPackages = digitalRegistries.getAll();
      allPackages.forEach(item -> packagesCache.put(item.getId(), item));
      return allPackages;
    }
    return new ArrayList<PackageDto>(packagesCache.values());
  }

  public PackageDto getById(int id) {
    if (packagesCache.isEmpty()) {
      List<PackageDto> allPackages = digitalRegistries.getAll();
      allPackages.forEach(item -> packagesCache.put(item.getId(), item));
    }
    return packagesCache.get(id);
  }
}
