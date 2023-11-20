package global.govstack.usct.controller;

import global.govstack.usct.controller.dto.BeneficiaryDto;
import global.govstack.usct.controller.dto.CandidateDto;
import global.govstack.usct.controller.dto.CreateBeneficiaryDto;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.service.BeneficiaryService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class BeneficiaryController {

  private final BeneficiaryService service;

  private final ModelMapper modelMapper;

  public BeneficiaryController(BeneficiaryService service, ModelMapper modelMapper) {
    this.service = service;
    this.modelMapper = modelMapper;
  }

  @PreAuthorize("hasRole('PAYMENT_OFFICER')")
  @GetMapping("/beneficiaries")
  public List<BeneficiaryDto> getAll() {
    return service.findAll();
  }

  @PreAuthorize("hasRole('PAYMENT_OFFICER')")
  @GetMapping("/beneficiaries/{id}")
  public BeneficiaryDto getBeneficiary(@PathVariable("id") int id) {
    return service.findById(id);
  }

  @PreAuthorize("hasRole('ENROLLMENT_OFFICER')")
  @PostMapping(value = "/beneficiaries")
  @ResponseStatus(HttpStatus.CREATED)
  public BeneficiaryDto create(@RequestBody final CreateBeneficiaryDto createBeneficiaryDto) {
    Candidate candidate = convertToEntity(createBeneficiaryDto.getCandidateDto());
    int enrolledPackageId = createBeneficiaryDto.getEnrolledPackage().getId();
    Beneficiary beneficiary = service.create(candidate, enrolledPackageId);
    return convertToDto(beneficiary);
  }

  private Candidate convertToEntity(CandidateDto candidateDto) {
    return modelMapper.map(candidateDto, Candidate.class);
  }

  private BeneficiaryDto convertToDto(Beneficiary beneficiary) {
    return modelMapper.map(beneficiary, BeneficiaryDto.class);
  }
}
