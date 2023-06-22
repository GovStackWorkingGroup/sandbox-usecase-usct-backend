create table PERSON
(
    ID                      INTEGER AUTO_INCREMENT,
    FOUNDATIONAL_ID         CHARACTER VARYING(255),
    FIRST_NAME              CHARACTER VARYING(255),
    LAST_NAME               CHARACTER VARYING(255),
    EMAIL                   CHARACTER VARYING(255),
    DATE_OF_BIRTH           CHARACTER VARYING(255),
    BANK_ACCOUNT_OWNER_NAME CHARACTER VARYING(255),
    FINANCIAL_ADDRESS       CHARACTER VARYING(255),
    FINANCIAL_MODALITY      CHARACTER VARYING(255),
    IBAN                    CHARACTER VARYING(255),
    BANK_NAME               CHARACTER VARYING(255),
    constraint ID
        primary key (ID)
);

create table CANDIDATE
(
    ID          INTEGER AUTO_INCREMENT NOT NULL,
    PERSON_ID   INTEGER,
    constraint CANDIDATE_PK
        primary key (ID),
    constraint "CANDIDATE_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON
);

create table PACKAGE
(
    ID          INTEGER AUTO_INCREMENT,
    NAME        CHARACTER VARYING(255),
    DESCRIPTION CHARACTER VARYING(255),
    constraint PK
        primary key (ID)
);

create table CANDIDATE_PACKAGE
(
    ID           INTEGER AUTO_INCREMENT,
    CANDIDATE_ID INTEGER NOT NULL,
    PACKAGE_ID   INTEGER NOT NULL,
    constraint CANDIDATE_PACKAGE_PK
        primary key (ID),
    constraint "CANDIDATE_PACKAGE_CANDIDATE_ID_fk"
        foreign key (CANDIDATE_ID) references CANDIDATE,
    constraint "CANDIDATE_PACKAGE_PACKAGE_ID_fk"
        foreign key (PACKAGE_ID) references PACKAGE
);

create table HOUSEHOLD_INFORMATION
(
    ID                INTEGER AUTO_INCREMENT,
    CANDIDATE_ID      INTEGER,
    RELATIVE_ID       INTEGER,
    RELATIONSHIP_TYPE CHARACTER VARYING(255) not null,
    constraint HOUSEHOLD_INFORMATION_PK
        primary key (ID),
    constraint CANIDATE_ID___FK
        foreign key (CANDIDATE_ID) references CANDIDATE,
    constraint "Relative__fk"
        foreign key (RELATIVE_ID) references PERSON
);


create table BENEFICIARY
(
    ID              INTEGER AUTO_INCREMENT,
    PERSON_ID    INTEGER,
    PACKAGE_ID      INTEGER,
    PAYMENT_STATUS          CHARACTER VARYING(255),
    constraint BENEFICIARY_PK
        primary key (ID),
    constraint "beneficiary_PACKAGE_id_fk"
        foreign key (PACKAGE_ID) references PACKAGE,
    constraint "beneficiary_CANDIDATE_ID_fk"
        foreign key (PERSON_ID) references PERSON

);


INSERT INTO PERSON(foundational_id, first_name, last_name, email, date_of_birth, bank_account_owner_name, financial_address, financial_modality, iban, bank_name)
values ( '9b237f8a-4dc2-4438-af0d-5f01c469b302', 'John', 'Smith', 'john.smith@example.com','28-04-1977',  'John Smith', 'Wilhelmstraße 12', 'Bank Account',
         'DE12 3456 7812 3456 7890', 'Golden Bank' ),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b302', 'Emma', 'Smith', 'emma.smith@example.com','28-04-1977',  'Emma Smith', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b302', 'Bob', 'Smith', 'bob.smith@example.com','28-04-1977',  'Bob Smith', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank');


INSERT INTO CANDIDATE(PERSON_ID) values ( 1 );

INSERT INTO PACKAGE (NAME, DESCRIPTION)
VALUES ('UBI', 'Universal basic income'),
       ('TPA', 'Targeted Poverty Alleviation'),
       ('SP', 'Social pension'),
       ('CFS', 'Child and Family support');

INSERT INTO CANDIDATE_PACKAGE (candidate_id, package_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

INSERT INTO HOUSEHOLD_INFORMATION (candidate_id, relative_id, relationship_type)
VALUES (1, 2, 'WIFE'),
       (1, 3, 'CHILD');

INSERT INTO BENEFICIARY (person_id, package_id, payment_status)
VALUES (1, 1, 'ACCEPTED');