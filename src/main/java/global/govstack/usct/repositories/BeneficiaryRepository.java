package global.govstack.usct.repositories;

import global.govstack.usct.model.Beneficiary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
  List<Beneficiary> findByPaymentOnboardingRequestId(String requestId);
}
