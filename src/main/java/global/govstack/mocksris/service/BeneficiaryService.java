package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentStatus;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficiaryService {
  private final BeneficiaryRepository repository;
  private final PaymentService paymentService;
  private final CandidateService candidateService;
  private final PaymentProperties properties;

  public BeneficiaryService(
      BeneficiaryRepository repository,
      PaymentService paymentService,
      CandidateService candidateService,
      PaymentProperties properties) {
    this.repository = repository;
    this.paymentService = paymentService;
    this.candidateService = candidateService;
    this.properties = properties;
  }

  public List<Beneficiary> findAll() {
    return repository.findAll();
  }

  public List<Beneficiary> saveAll(List<Beneficiary> beneficiaries) {
    return repository.saveAll(beneficiaries);
  }

  public Beneficiary findById(int id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Beneficiary with id: " + id + " doesn't exist"));
  }

  public List<Beneficiary> findByPaymentOnboardingRequestId(String requestId) {
    return repository.findByPaymentOnboardingRequestId(requestId);
  }

  @Transactional
  public Beneficiary create(Candidate candidate, Package enrolledPackage) {
    String functionalId =
        candidate.getPerson().getPersonalIdCode()
            + properties.governmentIdentifier()
            + enrolledPackage.getId();

    Beneficiary beneficiary = new Beneficiary();
    beneficiary.setPerson(candidate.getPerson());
    beneficiary.setEnrolledPackage(enrolledPackage);
    beneficiary.setPaymentStatus(PaymentStatus.INITIATE);
    beneficiary.setFunctionalId(functionalId);
    Beneficiary savedBeneficiary = repository.save(beneficiary);
    candidateService.deleteById(candidate.getId());

    paymentService.registerBeneficiary(List.of(savedBeneficiary));

    return savedBeneficiary;
  }

  public Beneficiary save(Beneficiary beneficiary) {
    return repository.save(beneficiary);
  }
}
