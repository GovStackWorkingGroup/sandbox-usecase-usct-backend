package global.govstack.mocksris.service;

import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.Package;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository repository;

    public BeneficiaryService(BeneficiaryRepository repository) {
        this.repository = repository;
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
       return repository.save(beneficiary);
    }
}
