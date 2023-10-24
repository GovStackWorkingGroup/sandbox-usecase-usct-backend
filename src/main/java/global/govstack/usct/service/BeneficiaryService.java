package global.govstack.usct.service;

import global.govstack.usct.configuration.PaymentProperties;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.model.Package;
import global.govstack.usct.repositories.BeneficiaryRepository;
import global.govstack.usct.types.PaymentStatus;
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
}
