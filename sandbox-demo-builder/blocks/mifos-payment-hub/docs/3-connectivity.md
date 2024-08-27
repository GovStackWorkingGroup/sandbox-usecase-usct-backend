# Connectivity

## X-Road connection 

### Port forward:
* `kubectl port-forward service/govstack-xroad-ssp 8000:4000 -n govstack`
* `kubectl port-forward service/govstack-xroad-ssc 7000:4000 -n govstack`

### Services endpoints:
* Fineract server: `https://fineract-server.paymenthub.svc.cluster.local:8443`
* Payment Hub Operations App: `http://ph-ee-operations-app.paymenthub.svc.cluster.local`


### X-ROAD API endpoints:

* Fineract server: `http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-fineract-server/`
* Payment Hub Operations App: `http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/`


### Example API calls:

#### Create client in Fineract:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-fineract-server/fineract-provider/api/v1/clients' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'accept: application/json, text/plain, */*' \
    --header 'accept-language: en-GB,en-US;q=0.9,en;q=0.8' \
    --header 'authorization: Basic {fineract-credentials}' \
    --header 'content-type: application/json;charset=UTF-8' \
    --header 'fineract-platform-tenantid: rhino' \
    --data '{
        "address": [],
        "familyMembers": [],
        "officeId": 1,
        "firstname": "Firstname",
        "lastname": "Lastname",
        "active": true,
        "legalFormId": 1,
        "locale": "en",
        "dateFormat": "dd MMMM yyyy",
        "activationDate": "19 April 2023",
        "submittedOnDate": "19 April 2023",
        "savingsProductId": null
    }'
```

#### Payment Hub Operations App Authorization:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/oauth/token?username={operations-app-username}&password={operations-app-password}&grant_type=password' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'Platform-TenantId: gorilla' \
    --header 'Authorization: Basic {operations-app-credentials}' \
    --header 'Content-Type: text/plain' \
    --data '{}'
```

#### Get users from Payment Hub Operations App:
```
    curl --location 'http://localhost:8080/r1/DEV/GOV/222/PROVIDER/Paymenthub-operations-app/api/v1/users' \
    --header 'X-Road-Client: DEV/GOV/111/CONSUMER' \
    --header 'Platform-TenantId: gorilla'
```