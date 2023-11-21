package global.govstack.usct.controller.dto;

import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.types.PaymentStatus;

public class BeneficiaryDto {
  private int id;
  private PersonDto person;
  private PaymentStatus paymentStatus;
  private PackageDto enrolledPackage;

  public BeneficiaryDto() {}

  public BeneficiaryDto(Beneficiary beneficiary, PackageDto enrolledPackage) {
    this.id = beneficiary.getId();
    this.person = new PersonDto(beneficiary.getPerson());
    this.paymentStatus = beneficiary.getPaymentStatus();
    this.enrolledPackage = enrolledPackage;
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
