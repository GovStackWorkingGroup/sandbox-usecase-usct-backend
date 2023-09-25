package global.govstack.mocksris.controller.dto;

public record CreatePersonDto(
    String personalIdCode,
    String firstName,
    String lastName,
    String email,
    String dateOfBirth,
    String region,
    String homeAddress,
    String phoneNumber,
    String occupation,
    String municipality,
    String zipCode,
    String bankAccountOwnerName,
    String financialAddress,
    String financialModality,
    String iban,
    String bankName) {}
