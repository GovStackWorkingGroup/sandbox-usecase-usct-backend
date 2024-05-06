package global.govstack.usct.service;

import static global.govstack.usct.types.PaymentStatus.ACCEPTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import global.govstack.usct.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.usct.configuration.PaymentHubBBInformationMediatorProperties;
import global.govstack.usct.configuration.PaymentHubProperties;
import global.govstack.usct.controller.dto.CreatePersonDto;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.model.Person;
import global.govstack.usct.repositories.BeneficiaryRepository;
import global.govstack.usct.repositories.PaymentDisbursementRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

class PaymentHubServiceTest {

  @Mock private PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties;

  @Mock private PaymentHubBBInformationMediatorProperties paymentHubBBInformationMediatorProperties;

  @Mock private PaymentHubProperties paymentHubProperties;

  @Mock private BeneficiaryRepository beneficiaryRepository;

  @Mock private PaymentDisbursementRepository paymentDisbursementRepository;

  @Mock private PackageService packageService;

  @Mock private RestTemplate restTemplate;

  @Mock private HttpComponentsClientHttpRequestFactory requestFactory;

  private PaymentHubService paymentHubService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    paymentHubService =
        new PaymentHubService(
            paymentHubProperties,
            paymentHubBBInformationMediatorProperties,
            requestFactory,
            beneficiaryRepository,
            paymentDisbursementRepository,
            packageService,
            restTemplate);
  }

  @Test
  void checkOrderPayment() {
    when(paymentHubProperties.bulkConnectorURL())
        .thenReturn("http://ph-ee-connector-bulk.paymenthub");
    when(restTemplate.postForObject(any(), any(), any())).thenReturn(null);
    var personDto =
        new CreatePersonDto(
            "personalIdCode",
            "John",
            "Doe",
            "john.doe@email.com",
            "dateOfBirth",
            "test-region",
            "test-street",
            "1234567",
            "smith",
            "test-munic",
            "12345",
            "johndoe",
            "test-address",
            "BANK_ACCOUNT",
            "1234567891234567",
            "test-bank");
    var person = new Person(personDto);
    var functionalId = "7485782570";
    var testOnboardedId = "testOnboardedId";
    Beneficiary beneficiary = new Beneficiary();
    beneficiary.setPerson(person);
    beneficiary.setEnrolledPackageId(1);
    beneficiary.setFunctionalId(functionalId);
    beneficiary.setPaymentStatus(ACCEPTED);
    beneficiary.setPaymentOnboardingRequestId(testOnboardedId);

    List<Beneficiary> beneficiaries = List.of(new Beneficiary());
    paymentHubService.orderPayment(beneficiaries);
    assertFalse(beneficiaries.isEmpty());
  }
}
