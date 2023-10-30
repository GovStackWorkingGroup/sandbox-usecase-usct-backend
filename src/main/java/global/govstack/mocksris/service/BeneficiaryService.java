package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Candidate;
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

  public Beneficiary findById(int id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Beneficiary with id: " + id + " doesn't exist"));
  }

  @Transactional
  public Beneficiary create(Candidate candidate, int enrolledPackageId) {
    String functionalId =
        candidate.getPerson().getPersonalIdCode()
            + properties.governmentIdentifier()
            + enrolledPackageId;

    Beneficiary beneficiary = new Beneficiary();
    beneficiary.setPerson(candidate.getPerson());
    beneficiary.setEnrolledPackageId(enrolledPackageId);
    beneficiary.setPaymentStatus(PaymentStatus.INITIATE);
    beneficiary.setFunctionalId(functionalId);
    Beneficiary savedBeneficiary = repository.save(beneficiary);
    candidateService.deleteById(candidate.getId());

    paymentService.registerBeneficiary(List.of(savedBeneficiary));

    return savedBeneficiary;
  }
}
