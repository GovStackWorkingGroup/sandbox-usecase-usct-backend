package global.govstack.mocksris.service;

import global.govstack.mocksris.model.BeneficiaryResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public BeneficiaryResponse createPayment() {

        // connect to X-Road find a paymentBB
        // make a payment requests.
        BeneficiaryResponse response = new BeneficiaryResponse();
        return response;

    }
}
