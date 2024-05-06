package global.govstack.usct.repositories;

import global.govstack.usct.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> findByIsBeneficiary (boolean isBeneficiary);
}
