package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.controller.dto.BeneficiaryDto;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository repository;
    private final PaymentService paymentService;
    private final CandidateService candidateService;
    private final PaymentProperties properties;
    private final PackageService packageService;

    public BeneficiaryService(
            BeneficiaryRepository repository,
            PaymentService paymentService,
            CandidateService candidateService,
            PaymentProperties properties, PackageService packageService) {
        this.repository = repository;
        this.paymentService = paymentService;
        this.candidateService = candidateService;
        this.properties = properties;
        this.packageService = packageService;
    }

    public List<BeneficiaryDto> findAll() {
        List<Beneficiary> beneficiaries = repository.findAll();
        return beneficiaries.stream().map(beneficiary -> {
            return new BeneficiaryDto(beneficiary, packageService.getById(beneficiary.getEnrolledPackageId()));
        }).toList();
    }

    public BeneficiaryDto findById(int id) {
        var beneficiary = repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiary with id: " + id + " doesn't exist"));
        return new BeneficiaryDto(beneficiary, packageService.getById(beneficiary.getEnrolledPackageId()));
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
