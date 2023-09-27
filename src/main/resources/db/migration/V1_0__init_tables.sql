create table PERSON
(
    ID                      INTEGER AUTO_INCREMENT,
    PERSONAL_ID_CODE        CHARACTER VARYING(255),
    FIRST_NAME              CHARACTER VARYING(255),
    LAST_NAME               CHARACTER VARYING(255),
    EMAIL                   CHARACTER VARYING(255),
    DATE_OF_BIRTH           CHARACTER VARYING(255),
    REGION                  CHARACTER VARYING(255),
    HOME_ADDRESS            CHARACTER VARYING(255),
    PHONE_NUMBER            CHARACTER VARYING(255),
    OCCUPATION              CHARACTER VARYING(255),
    MUNICIPALITY            CHARACTER VARYING(255),
    ZIP_CODE                CHARACTER VARYING(255),
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

create table BENEFICIARY
(
    ID                                      INTEGER AUTO_INCREMENT,
    PERSON_ID                               INTEGER,
    PACKAGE_ID                              INTEGER,
    FUNCTIONAL_ID                           CHARACTER VARYING(255),
    PAYMENT_STATUS                          CHARACTER VARYING(255),
    PAYMENT_ONBOARDING_STATUS               CHARACTER VARYING(255),
    PAYMENT_ONBOARDING_REQUEST_ID           CHARACTER VARYING(255),
    constraint BENEFICIARY_PK
        primary key (ID),
    constraint "beneficiary_PACKAGE_id_fk"
        foreign key (PACKAGE_ID) references PACKAGE,
    constraint "beneficiary_CANDIDATE_ID_fk"
        foreign key (PERSON_ID) references PERSON

);

INSERT INTO PERSON(personal_id_code, first_name, last_name, email, date_of_birth, region, home_address, phone_number,
                   occupation, municipality, zip_code, bank_account_owner_name, financial_address, financial_modality,
                   iban, bank_name)
values ('9b237f8a-4dc2-4438-af0d-5f01c469b302', 'John', 'Smith', 'john.smith@example.com', '28-04-1977', 'Patagonia',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210', 'John Smith',
        'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461001', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b310', 'Emma', 'Smith', 'emma.smith@example.com', '28-04-1977', 'Transylvania',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210', 'Emma Smith',
        'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461002', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b303', 'Liam', 'Anderson', 'liam.anderson@example.com', '28-04-1977',
        'Hokkaido', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Liam Anderson', 'Wilhelmstraße 12', 'MOBILE_MONEY', 'D8837461003', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b304', 'Olivia', 'Ramirez', 'olivia.ramirez@example.com', '28-04-1977',
        'Bavaria', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Olivia Ramirez', 'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461003', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b305', 'Ava', 'Patel', 'ava.patel@example.com', '28-04-1977', 'Tuscany',
        'Ava Patel', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461004', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b306', 'William', 'Roberts', 'william.roberts@example.com', '28-04-1977',
        'Cappadocia', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'William Roberts', 'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461005', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b307', 'Sophia', 'Campbell', 'sophia.campbell@example.com', '28-04-1977',
        'Yucatan Peninsula', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Sophia Campbell', 'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461006', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b308', 'Benjamin', 'Mitchell', 'benjamin.Mitchell@example.com', '28-04-1977',
        'Scottish Highlands', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Benjamin Mitchell', 'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461007', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b309', 'Isabella', 'Turner', 'isabella.turner@example.com', '28-04-1977',
        'Kalahari Desert', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Isabella Turner', 'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461008', 'Golden Bank'),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b311', 'Bob', 'Smith', 'bob.smith@example.com', '28-04-1977', 'Lapland',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', 'Bob Smith', '90210',
        'Wilhelmstraße 12', 'MOBILE_MONEY', '8837461008', 'Golden Bank');


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
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
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