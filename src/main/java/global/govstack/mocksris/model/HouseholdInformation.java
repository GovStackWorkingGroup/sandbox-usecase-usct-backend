package global.govstack.mocksris.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import global.govstack.mocksris.types.RelationshipType;



@Entity
public class HouseholdInformation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "candidate_id", nullable = false)
        private Candidate candidate;

        @ManyToOne
        @JoinColumn(name = "relative_id", nullable = false)
        private Person relative;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private RelationshipType RelationshipType;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public Candidate getCandidate() {
                return candidate;
        }

        public void setCandidate(Candidate candidate) {
                this.candidate = candidate;
        }

        public global.govstack.mocksris.types.RelationshipType getRelationshipType() {
                return RelationshipType;
        }

        public void setRelationshipType(global.govstack.mocksris.types.RelationshipType relationshipType) {
                RelationshipType = relationshipType;
        }

        public Person getRelative() {
                return relative;
        }

        public void setRelative(Person relative) {
                this.relative = relative;
        }
}
