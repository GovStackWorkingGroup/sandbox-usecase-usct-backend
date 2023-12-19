# Consent BB onboarding instruction 
DRAFT

## Create Data agreement 


## Create individuals
You can onboard individual by uploading a [CSV file](./consent/individuals.csv) or executing this [endpoint](https://consent-bb-swagger.igrant.io/v2023.11.1/index.html#post-/config/individual)

![img.png](consent/img/img.png)


### Recover your account for changing individual password

visit https://yopmail.com/en/

## Obtain an API key

Now you have setup Admin dashboard (for administrators) and Privacy dashboard (for individuals)

For the API integrations towards an application you need to obtain an API key:

![img_1.png](consent/img/img_1.png)


## Set header token

![img_3.png](consent/img/img_3.png)

## Examples of request

### GET individuals response

```json
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
        }
    ],
    "pagination": {
        "currentPage": 1,
        "totalItems": 2,
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