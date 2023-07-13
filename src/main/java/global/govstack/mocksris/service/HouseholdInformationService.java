package global.govstack.mocksris.service;

import global.govstack.mocksris.model.HouseholdInformation;
import global.govstack.mocksris.repositories.HouseholdInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdInformationService {

    private final HouseholdInformationRepository householdInformationRepository;

    public HouseholdInformationService(HouseholdInformationRepository householdInformationRepository) {
        this.householdInformationRepository = householdInformationRepository;
    }

    public List<HouseholdInformation> findAll() {
        return householdInformationRepository.findAll();
    }

    public HouseholdInformation findById(int id) {
        return householdInformationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HouseholdInformation with id: " + id + " doesn't exist"));
    }

    public List<HouseholdInformation> findAllRelatives(int personId) {
        return householdInformationRepository.findAllByPersonId(personId);
    }
}
