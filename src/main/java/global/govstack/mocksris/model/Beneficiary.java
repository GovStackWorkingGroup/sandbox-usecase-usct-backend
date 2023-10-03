package global.govstack.mocksris.model;

import global.govstack.mocksris.types.PaymentOnboardingStatus;
import global.govstack.mocksris.types.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Beneficiary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "package_id", nullable = false)
  private Package enrolledPackage;

  @Column private String functionalId;

  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;

  @Enumerated(EnumType.STRING)
  private PaymentOnboardingStatus paymentOnboardingStatus;

  @Column private String paymentOnboardingRequestId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public Package getEnrolledPackage() {
    return enrolledPackage;
  }

  public void setEnrolledPackage(Package enrolledPackage) {
    this.enrolledPackage = enrolledPackage;
  }

  public PaymentOnboardingStatus getPaymentOnboardingStatus() {
    return paymentOnboardingStatus;
  }

  public Beneficiary setPaymentOnboardingStatus(PaymentOnboardingStatus paymentOnboardingStatus) {
    this.paymentOnboardingStatus = paymentOnboardingStatus;
    return this;
  }

  public String getPaymentOnboardingRequestId() {
    return paymentOnboardingRequestId;
  }

  public Beneficiary setPaymentOnboardingRequestId(String paymentOnboardingRequestId) {
    this.paymentOnboardingRequestId = paymentOnboardingRequestId;
    return this;
  }

  public String getFunctionalId() {
    return functionalId;
  }

  public Beneficiary setFunctionalId(String functionalId) {
    this.functionalId = functionalId;
    return this;
  }
}
