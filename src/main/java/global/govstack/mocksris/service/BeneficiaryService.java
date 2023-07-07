package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.model.Person;
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

    private final PaymentProperties properties;

    public BeneficiaryService(BeneficiaryRepository repository, PaymentService paymentService, PaymentProperties properties) {
        this.repository = repository;
        this.paymentService = paymentService;
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
    public Beneficiary create(Person person, Package enrolledPackage) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setPerson(person);
        beneficiary.setEnrolledPackage(enrolledPackage);
        beneficiary.setPaymentStatus(PaymentStatus.INITIATE);
        Beneficiary savedBeneficiary = repository.save(beneficiary);
        String functionalId = savedBeneficiary.getPerson().getFoundationalId() +
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
