package global.govstack.usct.service;

import global.govstack.usct.model.Package;
import global.govstack.usct.repositories.PackageRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
  private final PackageRepository packageRepository;

  public PackageService(PackageRepository packageRepository) {
    this.packageRepository = packageRepository;
  }

  public List<Package> findAll() {
    return packageRepository.findAll();
  }

  public Package getById(int id) {
    return packageRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Package with id: " + id + " doesn't exist"));
  }
}
