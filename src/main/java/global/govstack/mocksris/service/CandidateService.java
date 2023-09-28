package global.govstack.mocksris.service;

import global.govstack.mocksris.controller.dto.CreateCandidateDto;
import global.govstack.mocksris.controller.dto.CreatePersonDto;
import global.govstack.mocksris.controller.dto.PackageDto;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.repositories.CandidateRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
  private final CandidateRepository candidateRepository;
  private final PersonService personService;
  private final ModelMapper modelMapper;

  public CandidateService(
      CandidateRepository candidateRepository,
      PersonService personService,
      ModelMapper modelMapper) {
    this.candidateRepository = candidateRepository;
    this.personService = personService;
    this.modelMapper = modelMapper;
  }

  public List<Candidate> findAll() {
    return candidateRepository.findAll();
  }

  public Candidate findById(int id) {
    return candidateRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Candidate with id: " + id + " doesn't exist"));
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
    Set<Package> packages =
        createCandidateDto.packages().stream()
            .map(this::convertToEntity)
            .collect(Collectors.toSet());

    candidate.setPackages(packages);
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
