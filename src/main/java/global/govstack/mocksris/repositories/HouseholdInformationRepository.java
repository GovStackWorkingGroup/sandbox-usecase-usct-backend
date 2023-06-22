package global.govstack.mocksris.repositories;

import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.model.HouseholdInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseholdInformationRepository extends JpaRepository<HouseholdInformation, Integer> {

    List<HouseholdInformation> findAllByCandidate(Candidate candidate);

}
