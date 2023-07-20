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
    AMOUNT      FLOAT,
    CURRENCY    CHARACTER VARYING(255),
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
    PERSON_ID         INTEGER,
    RELATIVE_ID       INTEGER,
    RELATIONSHIP_TYPE CHARACTER VARYING(255) not null,
    constraint HOUSEHOLD_INFORMATION_PK
        primary key (ID),
    constraint PERSON_ID___FK
        foreign key (PERSON_ID) references PERSON,
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
       ('9b237f8a-4dc2-4438-af0d-5f01c469b310', 'Emma', 'Smith', 'emma.smith@example.com','28-04-1977',  'Emma Smith', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b303', 'Liam', 'Anderson', 'liam.anderson@example.com','28-04-1977',  'Liam Anderson', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b304', 'Olivia', 'Ramirez', 'olivia.ramirez@example.com','28-04-1977',  'Olivia Ramirez', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b305', 'Ava', 'Patel', 'ava.patel@example.com','28-04-1977',  'Ava Patel', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b306', 'William', 'Roberts', 'william.roberts@example.com','28-04-1977',  'William Roberts', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b307', 'Sophia', 'Campbell', 'sophia.campbell@example.com','28-04-1977',  'Sophia Campbell', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b308', 'Benjamin', 'Mitchell', 'benjamin.Mitchell@example.com','28-04-1977',  'Benjamin Mitchell', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b309', 'Isabella', 'Turner', 'isabella.turner@example.com','28-04-1977',  'Isabella Turner', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b311', 'Bob', 'Smith', 'bob.smith@example.com','28-04-1977',  'Bob Smith', 'Wilhelmstraße 12', 'Bank Account',
        'DE12 3456 7812 3456 7890', 'Golden Bank');


INSERT INTO CANDIDATE(PERSON_ID) values
                                     ( 1 ),
                                     ( 2 ),
                                     ( 3 ),
                                     ( 4 ),
                                     ( 5 ),
                                     ( 6 ),
                                     ( 7 ),
                                     ( 8 ),
                                     ( 9 ),
                                     ( 10 );

INSERT INTO PACKAGE (NAME, DESCRIPTION, AMOUNT, CURRENCY)
VALUES ('UBI', 'Universal basic income', 1234.5, 'EURO'),
       ('TPA', 'Targeted Poverty Alleviation', 900, 'EURO'),
       ('SP', 'Social pension', 750, 'EURO'),
       ('CFS', 'Child and Family support', 500, 'EURO');

INSERT INTO CANDIDATE_PACKAGE (candidate_id, package_id)
VALUES (2, 1),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1),
       (5, 1),
       (5, 2),
       (5, 3),
       (6, 1),
       (6, 2),
       (6, 3),
       (6, 4),
       (7, 1),
       (7, 2),
       (7, 3),
       (8, 1),
       (8, 2),
       (8, 3),
       (9, 1),
       (9, 2),
       (9, 3);

INSERT INTO HOUSEHOLD_INFORMATION (person_id, relative_id, relationship_type)
VALUES (1, 2, 'WIFE'),
       (1, 3, 'CHILD');

INSERT INTO BENEFICIARY (person_id, package_id, payment_status)
VALUES (1, 1, 'ACCEPTED');