package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.HouseholdInformation;
import global.govstack.mocksris.types.RelationshipType;

public class RelativeDto {

    private PersonDto person;
    public RelationshipType relationshipType;

    public RelativeDto(HouseholdInformation householdInformation) {
        this.person = new PersonDto(householdInformation.getRelative());
        this.relationshipType = householdInformation.getRelationshipType();
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
}
