package global.govstack.usct.service;

import global.govstack.usct.configuration.IGrantProperties;
import global.govstack.usct.configuration.OpenImisProperties;
import global.govstack.usct.controller.dto.CandidateDto;
import global.govstack.usct.controller.dto.ConsentDto;
import global.govstack.usct.controller.dto.CreateCandidateDto;
import global.govstack.usct.controller.dto.CreatePersonDto;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.model.Person;
import global.govstack.usct.repositories.CandidateRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CandidateService {
  private final CandidateRepository candidateRepository;
  private final PersonService personService;
  private final PackageService packageService;
  private final ConsentService consentService;
  private final OpenImisProperties openImisProperties;
  private final IGrantProperties iGrantProperties;

  public CandidateService(
      CandidateRepository candidateRepository,
      PersonService personService,
      PackageService packageService,
      ConsentService consentService,
      OpenImisProperties openImisProperties,
      IGrantProperties iGrantProperties) {
    this.candidateRepository = candidateRepository;
    this.personService = personService;
    this.packageService = packageService;
    this.consentService = consentService;
    this.openImisProperties = openImisProperties;
    this.iGrantProperties = iGrantProperties;
  }

  public List<CandidateDto> findAll() {
    log.info("Get list of candidates");
    List<Candidate> candidates = candidateRepository.findAll();
    return candidates.stream()
        .map(
            candidate -> {
              List<PackageDto> packages = List.of();
              if (openImisProperties.mode().equals("emulator")) {
                packages = getPackageDtos(candidate.getEmulatorPackageIds());
              }
              if (openImisProperties.mode().equals("open-imis")) {
                packages = getPackageDtos(candidate.getOpenImisPackageIds());
              }
              ConsentDto consent = consentService.getConsent(candidate);
              return new CandidateDto(candidate, packages, consent);
            })
        .toList();
  }

  private List<PackageDto> getPackageDtos(Set<Integer> candidate) {
    return candidate.stream().map(packageService::getById).toList();
  }

  public CandidateDto findById(int id) {
    Candidate candidate =
        candidateRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Candidate with id: " + id + " doesn't exist"));
    List<PackageDto> packages = List.of();
    if (openImisProperties.mode().equals("open-imis")) {
      packages = getPackageDtos(candidate.getOpenImisPackageIds());
    }
    if (openImisProperties.mode().equals("emulator")) {
      packages = getPackageDtos(candidate.getEmulatorPackageIds());
    }
    ConsentDto consent = consentService.getConsent(candidate);
    return new CandidateDto(candidate, packages, consent);
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
    candidate.setOpenImisPackageIds(createCandidateDto.packageIds());
    return candidateRepository.save(candidate);
  }

  @Transactional
  public Candidate save(Candidate candidate) {
    personService.save(candidate.getPerson());
    return candidateRepository.save(candidate);
  }
}
