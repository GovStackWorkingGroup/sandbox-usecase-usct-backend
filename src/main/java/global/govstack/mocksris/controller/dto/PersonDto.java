package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.Person;

public class PersonDto {
  private int id;
  private String personalIdCode;
  private String firstName;
  private String lastName;
  private String email;
  private String dateOfBirth;
  private String region;
  private String homeAddress;
  private String phoneNumber;
  private String occupation;
  private String municipality;
  private String zipCode;
  private String bankAccountOwnerName;
  private String financialAddress;
  private String financialModality;
  private String iban;
  private String bankName;

  public PersonDto() {}

  public PersonDto(Person person) {
    this.id = person.getId();
    this.personalIdCode = person.getPersonalIdCode();
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.email = person.getEmail();
    this.dateOfBirth = person.getDateOfBirth();
    this.region = person.getRegion();
    this.homeAddress = person.getHomeAddress();
    this.phoneNumber = person.getPhoneNumber();
    this.occupation = person.getOccupation();
    this.municipality = person.getMunicipality();
    this.zipCode = person.getZipCode();
    this.bankAccountOwnerName = person.getBankAccountOwnerName();
    this.financialAddress = person.getFinancialAddress();
    this.financialModality = person.getFinancialModality().name();
    this.iban = person.getIban();
    this.bankName = person.getBankName();
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

  public void setPersonalIdCode(String personalIdCode) {
    this.personalIdCode = personalIdCode;
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

  public String getBankAccountOwnerName() {
    return bankAccountOwnerName;
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

  public String getFinancialModality() {
    return financialModality;
  }

  public void setFinancialModality(String financialModality) {
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
