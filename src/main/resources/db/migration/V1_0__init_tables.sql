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
    OPENIMIS_PACKAGE_ID  INTEGER ARRAY,
    EMULATOR_PACKAGE_ID  INTEGER ARRAY,
    CONSENT_ID  INT,
    I_GRANT_ID  CHARACTER VARYING(255),
    constraint CANDIDATE_PK
        primary key (ID),
    constraint "CANDIDATE_PERSON_ID_fk"
        foreign key (PERSON_ID) references PERSON
);

create table CONSENT
(
    ID             INTEGER AUTO_INCREMENT NOT NULL,
    STATUS         CHARACTER VARYING(255),
    DATE           CHARACTER VARYING(255),
    constraint CONSENT_PK
        primary key (ID)
);

create table BENEFICIARY
(
    ID                      INTEGER AUTO_INCREMENT,
    PERSON_ID               INTEGER,
    PACKAGE_ID              INTEGER,
    PAYMENT_STATUS          CHARACTER VARYING(255),
    FUNCTIONAL_ID                           CHARACTER VARYING(255),
    PAYMENT_ONBOARDING_STATUS               CHARACTER VARYING(255),
    PAYMENT_ONBOARDING_REQUEST_ID           CHARACTER VARYING(255),
    constraint BENEFICIARY_PK
        primary key (ID),
    constraint "beneficiary_CANDIDATE_ID_fk"
        foreign key (PERSON_ID) references PERSON

);


INSERT INTO PERSON(personal_id_code, first_name, last_name, email, date_of_birth, region, home_address, phone_number,
                   occupation, municipality, zip_code, bank_account_owner_name, financial_address, financial_modality,
                   iban, bank_name)
values ('9b237f8a-4dc2-4438-af0d-5f01c469b302', 'John', 'Doe', 'john.doe@example.com', '28-04-1977', 'Patagonia',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210', 'John Doe',
        '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b310', 'John', 'Smith', 'john.smith@example.com', '28-04-1977', 'Transylvania',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210', 'John Smith',
        '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b303', 'Liam', 'Anderson', 'liam.anderson@example.com', '28-04-1977',
        'Hokkaido', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Liam Anderson', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b304', 'Olivia', 'Ramirez', 'olivia.ramirez@example.com', '28-04-1977',
        'Bavaria', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Olivia Ramirez', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b305', 'Ava', 'Patel', 'ava.patel@example.com', '28-04-1977', 'Tuscany',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Ava Patel', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b306', 'William', 'Roberts', 'william.roberts@example.com', '28-04-1977',
        'Cappadocia', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'William Roberts', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b307', 'Sophia', 'Campbell', 'sophia.campbell@example.com', '28-04-1977',
        'Yucatan Peninsula', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Sophia Campbell', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b308', 'Benjamin', 'Mitchell', 'benjamin.mitchell@example.com', '28-04-1977',
        'Scottish Highlands', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Benjamin Mitchell', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b309', 'Isabella', 'Turner', 'isabella.turner@example.com', '28-04-1977',
        'Kalahari Desert', '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210',
        'Isabella Turner', '8837461001', 'MOBILE_MONEY', '', ''),
       ('9b237f8a-4dc2-4438-af0d-5f01c469b311', 'Bob', 'Smith', 'bob.smith@example.com', '28-04-1977', 'Lapland',
        '1234 Elm Street, Apartment 567', '18011234567', 'Archaeologist', 'Willow Creek', '90210', 'Bob Smith',
        '8837461001', 'MOBILE_MONEY', '', '');

INSERT INTO CONSENT(ID, STATUS, DATE)
values (1,'GRANTED', '2023-11-20T12:30:00');

INSERT INTO CANDIDATE(PERSON_ID, OPENIMIS_PACKAGE_ID, EMULATOR_PACKAGE_ID, CONSENT_ID, I_GRANT_ID)
values (1, ARRAY[147, 148, 149], ARRAY[1, 2, 3], 1, '658018417fb9d4055b5e60a0'),
       (2, ARRAY[147, 149], ARRAY[1, 3], null, '65801da67fb9d4055b5e60a4'),
       (3, ARRAY[147, 148, 149, 150], ARRAY[1, 2, 3, 4], null, '658064c1a1cea46145a801fa'),
       (4, ARRAY[147], ARRAY[1], null, '658064c1a1cea46145a801fc'),
       (5, ARRAY[147, 148, 149], ARRAY[1, 2, 3], null, '6580659aa1cea46145a801ff'),
       (6, ARRAY[147, 148, 149, 150], ARRAY[1, 2, 3, 4], null, '6580659aa1cea46145a80201'),
       (7, ARRAY[147, 148, 149], ARRAY[1, 2, 3], null, '6580659aa1cea46145a80203'),
       (8, ARRAY[147, 148, 149], ARRAY[1, 2, 3], null, '6580659aa1cea46145a80205'),
       (9, ARRAY[147, 148, 149], ARRAY[1, 2, 3], null, '6580659aa1cea46145a80207'),
       (10, ARRAY[], ARRAY[], null, '6580659aa1cea46145a80209');
