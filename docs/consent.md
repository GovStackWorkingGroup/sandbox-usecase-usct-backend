# Consent BB onboarding instruction 
DRAFT

## Create Data agreement 


## Create individuals
You can onboard individual by uploading a [CSV file](./consent/individuals.csv) or executing this [endpoint](https://consent-bb-swagger.igrant.io/v2023.11.1/index.html#post-/config/individual)

![img.png](consent/img/img.png)

password: qwerty123


### Recover your account for changing individual password

visit https://yopmail.com/en/

## Obtain an API key

Now you have set up Admin dashboard (for administrators) and Privacy dashboard (for individuals)

For the API integrations towards an application you need to obtain an API key:

![img_1.png](consent/img/img_1.png)


## Set header token

![img_3.png](consent/img/img_3.png)

## Examples of request

### GET individuals response

```json
 {
  "individuals": [
    {
      "id": "658018417fb9d4055b5e60a0",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "John Doe",
      "iamId": "57835a0b-65ea-4745-9546-f9607c298faa",
      "email": "johndoe@yopmail.com",
      "phone": "918848245077"
    },
    {
      "id": "65801da67fb9d4055b5e60a4",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "John Smith",
      "iamId": "436e1690-6d33-40c4-afea-3a7da14f1afd",
      "email": "johnsmith@yopmail.com",
      "phone": "918848245078"
    },
    {
      "id": "658064c1a1cea46145a801fa",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Liam Anderson",
      "iamId": "65c389eb-efac-4f8e-b395-bf5044163a6a",
      "email": "liam.anderson@yopmail.com",
      "phone": "918848245079"
    },
    {
      "id": "658064c1a1cea46145a801fc",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Olivia Ramirez",
      "iamId": "0f0f772e-38fb-4ee1-a3c0-ce709ce1fbda",
      "email": "olivia.ramirez@yopmail.com",
      "phone": "918848245080"
    },
    {
      "id": "6580659aa1cea46145a801ff",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Ava Patel",
      "iamId": "b06078be-9718-49d6-899a-14b9cd54767b",
      "email": "ava.patel@yopmail.com",
      "phone": "918848245081"
    },
    {
      "id": "6580659aa1cea46145a80201",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "William Roberts",
      "iamId": "2b4132da-a10d-43c8-bece-cc9defc80c50",
      "email": "william.roberts@yopmail.com",
      "phone": "918848245082"
    },
    {
      "id": "6580659aa1cea46145a80203",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Sophia Campbell",
      "iamId": "fcb53ce9-d364-4a12-948d-a391c9d7d737",
      "email": "sophia.campbell@yopmail.com",
      "phone": "918848245083"
    },
    {
      "id": "6580659aa1cea46145a80205",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Benjamin Mitchell",
      "iamId": "c91e9a26-3e41-437e-b99c-fe001ccbc7ef",
      "email": "benjamin.mitchell@yopmail.com",
      "phone": "918848245084"
    },
    {
      "id": "6580659aa1cea46145a80207",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Isabella Turner",
      "iamId": "1152ffd8-ee63-4131-888a-73ab6453aec7",
      "email": "isabella.turner@yopmail.com",
      "phone": "918848245085"
    },
    {
      "id": "6580659aa1cea46145a80209",
      "externalId": "",
      "externalIdType": "",
      "identityProviderId": "",
      "name": "Bob Smith",
      "iamId": "0b6329a3-b918-45a2-9b22-f210d8506cb0",
      "email": "bob.smith@yopmail.com",
      "phone": "918848245086"
    }
  ],
  "pagination": {
    "currentPage": 1,
    "totalItems": 10,
    "totalPages": 1,
    "limit": 10,
    "hasPrevious": false,
    "hasNext": false
  }
}
```


### Create consent response

```json
{
    "consentRecord": {
        "id": "65805ff6a1cea46145a80186",
        "dataAgreementId": "6580176d7fb9d4055b5e608d",
        "dataAgreementRevisionId": "658017877fb9d4055b5e6098",
        "dataAgreementRevisionHash": "53c68b3cc3d0496123c400d3a65e1752f6f3fe66",
        "individualId": "65801da67fb9d4055b5e60a4",
        "optIn": true,
        "state": "unsigned",
        "signatureId": ""
    },
    "revision": {
        "id": "65805ff6a1cea46145a80187",
        "schemaName": "consentRecord",
        "objectId": "65805ff6a1cea46145a80186",
        "signedWithoutObjectId": false,
        "timestamp": "2023-12-18T15:06:30Z",
        "authorizedByIndividualId": "",
        "authorizedByOtherId": "65801da67fb9d4055b5e60a4",
        "predecessorHash": "",
        "predecessorSignature": "",
        "objectData": "{\"id\":\"65805ff6a1cea46145a80186\",\"dataAgreementId\":\"6580176d7fb9d4055b5e608d\",\"dataAgreementRevisionId\":\"658017877fb9d4055b5e6098\",\"dataAgreementRevisionHash\":\"53c68b3cc3d0496123c400d3a65e1752f6f3fe66\",\"individualId\":\"65801da67fb9d4055b5e60a4\",\"optIn\":true,\"state\":\"unsigned\",\"signatureId\":\"\"}"
    }
}
```

## OptIn value

https://iam.consent-bb.playground.sandbox-playground.com/

### Response

```json
{
    "consentRecord": {
        "id": "65805ff6a1cea46145a80186",
        "dataAgreementId": "6580176d7fb9d4055b5e608d",
        "dataAgreementRevisionId": "658017877fb9d4055b5e6098",
        "dataAgreementRevisionHash": "53c68b3cc3d0496123c400d3a65e1752f6f3fe66",
        "individualId": "65801da67fb9d4055b5e60a4",
        "optIn": true,
        "state": "unsigned",
        "signatureId": ""
    },
    "revision": {
        "id": "65806366a1cea46145a801af",
        "schemaName": "consentRecord",
        "objectId": "65805ff6a1cea46145a80186",
        "signedWithoutObjectId": false,
        "timestamp": "2023-12-18T15:21:10Z",
        "authorizedByIndividualId": "",
        "authorizedByOtherId": "65801da67fb9d4055b5e60a4",
        "predecessorHash": "ae6ccd289764e6a96c9fa3a33151e9578468e08a",
        "predecessorSignature": "",
        "objectData": "{\"id\":\"65805ff6a1cea46145a80186\",\"dataAgreementId\":\"6580176d7fb9d4055b5e608d\",\"dataAgreementRevisionId\":\"658062dda1cea46145a801a1\",\"dataAgreementRevisionHash\":\"8f3460c2dc6d588a510ea00bb476bb14a951d2d2\",\"individualId\":\"65801da67fb9d4055b5e60a4\",\"optIn\":true,\"state\":\"unsigned\",\"signatureId\":\"\"}"
    }
}
```