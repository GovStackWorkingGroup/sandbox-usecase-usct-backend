package global.govstack.usct.repositories;

import global.govstack.usct.model.Candidate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
  List<Candidate> findByIsBeneficiary(boolean isBeneficiary);
}
