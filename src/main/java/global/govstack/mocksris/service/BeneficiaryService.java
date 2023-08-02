package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDetailsDTO;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository repository;
    private final PaymentService paymentService;
    private final CandidateService candidateService;
    private final PaymentProperties properties;

    public BeneficiaryService(BeneficiaryRepository repository, PaymentService paymentService, CandidateService candidateService, PaymentProperties properties) {
        this.repository = repository;
        this.paymentService = paymentService;
        this.candidateService = candidateService;
        this.properties = properties;
    }

    public List<Beneficiary> findAll() {
        return repository.findAll();
    }

    public Beneficiary findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiary with id: " + id + " doesn't exist"));
    }

    @Transactional
    public Beneficiary create(Candidate candidate, Package enrolledPackage) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setPerson(candidate.getPerson());
        beneficiary.setEnrolledPackage(enrolledPackage);
        beneficiary.setPaymentStatus(PaymentStatus.INITIATE);
        Beneficiary savedBeneficiary = repository.save(beneficiary);
        candidateService.deleteById(candidate.getId());
        String functionalId = savedBeneficiary.getPerson().getPersonalIdCode() +
                properties.governmentIdentifier() +
                savedBeneficiary.getEnrolledPackage().getId();
        var beneficiaryDTO = List.of(new PaymentOnboardingBeneficiaryDetailsDTO(
                functionalId,
                savedBeneficiary.getPerson().getFinancialModality(),
                savedBeneficiary.getPerson().getFinancialAddress()
        ));
        var requestID = UUID.randomUUID().toString();
        var paymentDto = new PaymentOnboardingBeneficiaryDTO(requestID, properties.sourceBbId(), beneficiaryDTO);
        paymentService.registerBeneficiary(paymentDto);
        return savedBeneficiary;
    }
}
