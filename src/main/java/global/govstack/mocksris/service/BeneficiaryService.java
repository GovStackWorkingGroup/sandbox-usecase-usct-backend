package global.govstack.mocksris.service;

import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDetailsDTO;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository repository;
    private final PaymentService paymentService;

    private final String MOCK_SRIS_BB = "MOCK-SRIS-BB";
    private final String GOVERNMENT_IDENTIFIER = "066283";
    private final String PROGRAM_ID = "9876";
    private final String REQUEST_ID = "requestId";

    public BeneficiaryService(BeneficiaryRepository repository, PaymentService paymentService) {
        this.repository = repository;
        this.paymentService = paymentService;
    }

    public List<Beneficiary> findAll() {
        return repository.findAll();
    }

    public Beneficiary findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiary with id: " + id + " doesn't exist"));
    }

    public Beneficiary create(Person person, Package enrolledPackage) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setPerson(person);
        beneficiary.setEnrolledPackage(enrolledPackage);
        beneficiary.setPaymentStatus(PaymentStatus.INITIATE);
        Beneficiary savedBeneficiary = repository.save(beneficiary);
        String functionalId = savedBeneficiary.getPerson().getFoundationalId()+ GOVERNMENT_IDENTIFIER + PROGRAM_ID;
        var beneficiaryDTO = List.of(new PaymentOnboardingBeneficiaryDetailsDTO(
                functionalId,
                savedBeneficiary.getPerson().getFinancialModality(),
                savedBeneficiary.getPerson().getFinancialAddress()
        ));
        var paymentDto = new PaymentOnboardingBeneficiaryDTO("stringstring", MOCK_SRIS_BB, beneficiaryDTO);
        paymentService.registerBeneficiary(paymentDto);
        return savedBeneficiary;
    }
}
