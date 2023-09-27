package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.types.PaymentStatus;

public class BeneficiaryDto {
  private int id;
  private PersonDto person;
  private PaymentStatus paymentStatus;
  private PackageDto enrolledPackage;

  public BeneficiaryDto() {}

  public BeneficiaryDto(Beneficiary beneficiary) {
    this.id = beneficiary.getId();
    this.person = new PersonDto(beneficiary.getPerson());
    this.paymentStatus = beneficiary.getPaymentStatus();
    this.enrolledPackage = new PackageDto(beneficiary.getEnrolledPackage());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PersonDto getPerson() {
    return person;
  }

  public void setPerson(PersonDto person) {
    this.person = person;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public PackageDto getEnrolledPackage() {
    return enrolledPackage;
  }

  public void setEnrolledPackage(PackageDto enrolledPackage) {
    this.enrolledPackage = enrolledPackage;
  }
}
