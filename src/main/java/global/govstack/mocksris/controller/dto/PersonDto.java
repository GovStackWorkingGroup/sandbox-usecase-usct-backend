package global.govstack.mocksris.controller.dto;


import global.govstack.mocksris.model.Person;

public class PersonDto {
    private int id;
    private String  foundationalId;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  dateOfBirth;
    private String  bankAccountOwnerName;
    private String  financialAddress;
    private String  financialModality;
    private String  iban;
    private String  bankName;

    public PersonDto() {
    }

    public PersonDto(Person person) {
        this.id = person.getId();
        this.foundationalId = person.getFoundationalId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.dateOfBirth = person.getDateOfBirth();
        this.bankAccountOwnerName = person.getBankAccountOwnerName();
        this.financialAddress = person.getFinancialAddress();
        this.financialModality = person.getFinancialModality();
        this.iban = person.getIban();
        this.bankName = person.getBankName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoundationalId() {
        return foundationalId;
    }

    public void setFoundationalId(String foundationalId) {
        this.foundationalId = foundationalId;
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
