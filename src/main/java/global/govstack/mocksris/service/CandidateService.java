package global.govstack.mocksris.service;

import global.govstack.mocksris.controller.dto.CandidateDto;
import global.govstack.mocksris.controller.dto.CreateCandidateDto;
import global.govstack.mocksris.controller.dto.CreatePersonDto;
import global.govstack.mocksris.controller.dto.PackageDto;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.repositories.CandidateRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
  private final CandidateRepository candidateRepository;
  private final PersonService personService;
  private final ModelMapper modelMapper;

  private final PackageService packageService;
  private Map<Integer, PackageDto> packagesCache;


  public CandidateService(
          CandidateRepository candidateRepository,
          PersonService personService,
          ModelMapper modelMapper, PackageService packageService) {
    this.candidateRepository = candidateRepository;
    this.personService = personService;
    this.modelMapper = modelMapper;
    this.packageService = packageService;
    this.packagesCache = new ConcurrentHashMap<>();
  }

  public List<CandidateDto> findAll() {
    List<PackageDto> allPackages = packageService.findAll();
    allPackages.forEach(item -> packagesCache.put(item.getId(), item));
    List<Candidate> candidates = candidateRepository.findAll();
    return candidates.stream().map(candidate -> {
      List<PackageDto> packageDtoList = candidate.getPackageIds().stream().map(packageId -> packagesCache.get(packageId)).toList();
      return new CandidateDto(candidate, packageDtoList);
    }).toList();
  }

  public CandidateDto findById(int id) {
      Candidate candidate = candidateRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Candidate with id: " + id + " doesn't exist"));
      List<PackageDto> packageDtoList = candidate.getPackageIds().stream().map(packageId -> packagesCache.get(packageId)).toList();
     return new CandidateDto(candidate, packageDtoList);
  }

  public void deleteById(Integer id) {
    candidateRepository.deleteById(id);
  }

  @Transactional
  public Candidate save(CreateCandidateDto createCandidateDto) {
    CreatePersonDto createPersonDto = createCandidateDto.person();
    Person person = personService.save(createPersonDto);
    Candidate candidate = new Candidate();
    candidate.setPerson(person);
    candidate.setPackageIds(createCandidateDto.packageIds());
    return candidateRepository.save(candidate);
  }

  @Transactional
  public Candidate save(Candidate candidate) {
    personService.save(candidate.getPerson());
    return candidateRepository.save(candidate);
  }

  private Package convertToEntity(PackageDto packageDto) {
    return modelMapper.map(packageDto, Package.class);
  }
}
