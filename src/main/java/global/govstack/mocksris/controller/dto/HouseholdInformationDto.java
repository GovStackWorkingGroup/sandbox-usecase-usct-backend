package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.HouseholdInformation;
import global.govstack.mocksris.types.RelationshipType;

public class HouseholdInformationDto {

    private int id;
    private PersonDto person;
    private PersonDto relative;
    private RelationshipType relationshipType;

    public HouseholdInformationDto(HouseholdInformation householdInformation) {
        this.id = householdInformation.getId();
        this.person = new PersonDto(householdInformation.getPerson());
        this.relative = new PersonDto(householdInformation.getRelative());
        this.relationshipType = householdInformation.getRelationshipType();
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

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public PersonDto getRelative() {
        return relative;
    }

    public void setRelative(PersonDto relative) {
        this.relative = relative;
    }
}
