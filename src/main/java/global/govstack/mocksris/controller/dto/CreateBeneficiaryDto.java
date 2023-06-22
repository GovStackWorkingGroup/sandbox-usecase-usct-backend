package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.Beneficiary;

public class CreateBeneficiaryDto {
    private PersonDto person;
    private PackageDto enrolledPackage;

    public CreateBeneficiaryDto(PersonDto person, PackageDto enrolledPackage) {
        this.person = person;
        this.enrolledPackage = enrolledPackage;
    }

    public CreateBeneficiaryDto(Beneficiary beneficiary) {
        this.person = new PersonDto(beneficiary.getPerson());
        this.enrolledPackage = new PackageDto(beneficiary.getEnrolledPackage());
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public PackageDto getEnrolledPackage() {
        return enrolledPackage;
    }

    public void setEnrolledPackage(PackageDto enrolledPackage) {
        this.enrolledPackage = enrolledPackage;
    }
}
