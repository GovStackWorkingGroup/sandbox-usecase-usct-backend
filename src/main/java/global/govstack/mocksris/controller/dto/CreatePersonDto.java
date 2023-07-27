package global.govstack.mocksris.controller.dto;

public record CreatePersonDto(String  foundationalId, String  firstName, String  lastName, String  email,
                              String  dateOfBirth, String  bankAccountOwnerName, String  financialAddress,
                              String  financialModality,  String  iban,  String  bankName) {
}
