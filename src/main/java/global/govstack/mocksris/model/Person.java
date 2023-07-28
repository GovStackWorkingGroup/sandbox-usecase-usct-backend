package global.govstack.mocksris.model;

import global.govstack.mocksris.controller.dto.CreatePersonDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(nullable = false)
    private String foundationalId;
    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String dateOfBirth;

    @Column
    private String bankAccountOwnerName;

    @Column
    private String financialAddress;

    @Column
    private String financialModality;

    @Column
    private String iban;

    @Column
    private String bankName;

    public Person() {

    }

    public Person(CreatePersonDto personDto) {
        this.foundationalId = personDto.foundationalId();
        this.firstName = personDto.firstName();
        this.lastName = personDto.lastName();
        this.email = personDto.email();
        this.dateOfBirth = personDto.dateOfBirth();
        this.bankAccountOwnerName = personDto.bankAccountOwnerName();
        this.financialAddress = personDto.financialAddress();
        this.financialModality = personDto.financialModality();
        this.iban = personDto.iban();
        this.bankName = personDto.bankName();
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
