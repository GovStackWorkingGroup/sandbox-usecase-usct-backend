package global.govstack.mocksris.model;

import global.govstack.mocksris.controller.dto.CreatePersonDto;
import global.govstack.mocksris.types.PaymentModality;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(nullable = false)
  private String personalIdCode;

  @Column(nullable = false)
  private String firstName;

  @Column private String lastName;

  @Column private String email;

  @Column private String dateOfBirth;

  @Column private String region;

  @Column private String homeAddress;
  @Column private String phoneNumber;

  @Column private String occupation;

  @Column private String municipality;
  @Column private String zipCode;

  @Column private String bankAccountOwnerName;

  @Column private String financialAddress;

  @Column
  @Enumerated(EnumType.STRING)
  private PaymentModality financialModality;

  @Column private String iban;

  @Column private String bankName;

  public Person() {}

  public Person(CreatePersonDto personDto) {
    this.personalIdCode = personDto.personalIdCode();
    this.firstName = personDto.firstName();
    this.lastName = personDto.lastName();
    this.email = personDto.email();
    this.dateOfBirth = personDto.dateOfBirth();
    this.region = personDto.region();
    this.homeAddress = personDto.homeAddress();
    this.phoneNumber = personDto.phoneNumber();
    this.occupation = personDto.occupation();
    this.municipality = personDto.municipality();
    this.zipCode = personDto.zipCode();
    this.bankAccountOwnerName = personDto.bankAccountOwnerName();
    this.financialAddress = personDto.financialAddress();
    this.financialModality = PaymentModality.valueOf(personDto.financialModality());
    this.iban = personDto.iban();
    this.bankName = personDto.bankName();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPersonalIdCode() {
    return personalIdCode;
  }

  public void setPersonalIdCode(String foundationalId) {
    this.personalIdCode = foundationalId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getBankAccountOwnerName() {
    return bankAccountOwnerName;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(String homeAddress) {
    this.homeAddress = homeAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public void setBankAccountOwnerName(String bankAccountOwnerName) {
    this.bankAccountOwnerName = bankAccountOwnerName;
  }

  public String getFinancialAddress() {
    return financialAddress;
  }

  public void setFinancialAddress(String financialAddress) {
    this.financialAddress = financialAddress;
  }

  public PaymentModality getFinancialModality() {
    return financialModality;
  }

  public void setFinancialModality(PaymentModality financialModality) {
    this.financialModality = financialModality;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
}
