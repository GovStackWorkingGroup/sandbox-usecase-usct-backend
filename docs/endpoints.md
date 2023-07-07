# Endpoints

## Access swagger:
```
http://localhost:8080/swagger-ui/index.html
```

## Beneficiary

### Order payment
POST

`/api/v1/payment/order-payment`

Request body

```json
[
  {
    "id": 0,
    "person": {
      "id": 0,
      "foundationalId": "string",
      "firstName": "string",
      "lastName": "string",
      "email": "string",
      "dateOfBirth": "string",
      "bankAccountOwnerName": "string",
      "financialAddress": "string",
      "financialModality": "string",
      "iban": "string",
      "bankName": "string"
    },
    "paymentStatus": "INITIATE",
    "enrolledPackage": {
      "id": 0,
      "name": "string",
      "description": "string"
    }
  }
]
```

### Get all
`/api/v1/beneficiaries`

Response body

```json
[
  {
    "id": 1,
    "person": {
      "id": 1,
      "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
      "firstName": "John",
      "lastName": "Smith",
      "email": "john.smith@example.com",
      "dateOfBirth": "28-04-1977",
      "bankAccountOwnerName": "John Smith",
      "financialAddress": "Wilhelmstraße 12",
      "financialModality": "Bank Account",
      "iban": "DE12 3456 7812 3456 7890",
      "bankName": "Golden Bank"
    },
    "paymentStatus": "ACCEPTED",
    "enrolledPackage": {
      "id": 1,
      "name": "UBI",
      "description": "Universal basic income"
    }
  }
]
```

### Create 

Post

`/api/v1/beneficiaries`

#### Request object

```json
 {
  "person": {
    "id": 2,
    "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "dateOfBirth": "28-04-1977",
    "bankAccountOwnerName": "John Smith",
    "financialAddress": "Wilhelmstraße 12",
    "financialModality": "Bank Account",
    "iban": "DE12 3456 7812 3456 7890",
    "bankName": "Golden Bank"
  },
  "enrolledPackage": {
    "id": 1,
    "name": "UBI",
    "description": "Universal basic income"
  }
}
```

#### Response body

```json
{
  "id": 2,
  "person": {
    "id": 2,
    "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "dateOfBirth": "28-04-1977",
    "bankAccountOwnerName": "John Smith",
    "financialAddress": "Wilhelmstraße 12",
    "financialModality": "Bank Account",
    "iban": "DE12 3456 7812 3456 7890",
    "bankName": "Golden Bank"
  },
  "paymentStatus": "INITIATE",
  "enrolledPackage": {
    "id": 1,
    "name": "UBI",
    "description": "Universal basic income"
  }
}
```

### Get by id

`/api/v1/beneficiaries/{id}`

Response body

```json
{
  "id": 1,
  "person": {
    "id": 1,
    "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "dateOfBirth": "28-04-1977",
    "bankAccountOwnerName": "John Smith",
    "financialAddress": "Wilhelmstraße 12",
    "financialModality": "Bank Account",
    "iban": "DE12 3456 7812 3456 7890",
    "bankName": "Golden Bank"
  },
  "paymentStatus": "ACCEPTED",
  "enrolledPackage": {
    "id": 1,
    "name": "UBI",
    "description": "Universal basic income"
  }
}
```

## Candidate

### Get relatives

`/api/v1/relatives/{candidateId}`

```json
[
  {
    "person": {
      "id": 2,
      "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
      "firstName": "Emma",
      "lastName": "Smith",
      "email": "emma.smith@example.com",
      "dateOfBirth": "28-04-1977",
      "bankAccountOwnerName": "Emma Smith",
      "financialAddress": "Wilhelmstraße 12",
      "financialModality": "Bank Account",
      "iban": "DE12 3456 7812 3456 7890",
      "bankName": "Golden Bank"
    },
    "relationshipType": "WIFE"
  }
]
```

### Get all

`/api/v1/candidates`

Response body

```json
[
  {
    "id": 1,
    "person": {
      "id": 1,
      "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
      "firstName": "John",
      "lastName": "Smith",
      "email": "john.smith@example.com",
      "dateOfBirth": "28-04-1977",
      "bankAccountOwnerName": "John Smith",
      "financialAddress": "Wilhelmstraße 12",
      "financialModality": "Bank Account",
      "iban": "DE12 3456 7812 3456 7890",
      "bankName": "Golden Bank"
    },
    "packages": [
      {
        "id": 1,
        "name": "UBI",
        "description": "Universal basic income"
      }
    ]
  }
]
```

### Get by id

`/api/v1/candidates/{id}`

Response body

```json
{
  "id": 1,
  "person": {
    "id": 1,
    "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "dateOfBirth": "28-04-1977",
    "bankAccountOwnerName": "John Smith",
    "financialAddress": "Wilhelmstraße 12",
    "financialModality": "Bank Account",
    "iban": "DE12 3456 7812 3456 7890",
    "bankName": "Golden Bank"
  },
  "packages": [
    {
      "id": 1,
      "name": "UBI",
      "description": "Universal basic income"
    }
  ]
}
```

## Package

### Get all

`/api/v1/packages`

Response body

```json
[
  {
    "id": 1,
    "name": "UBI",
    "description": "Universal basic income"
  }
]
```

### Get by id

`/api/v1/packages/{id}`

Response body

```json
{
  "id": 1,
  "name": "UBI",
  "description": "Universal basic income"
}
```

## Household-information

### Get all

`/api/v1/household-information`

Response body

```json
[
  {
    "id": 1,
    "candidate": {
      "id": 1,
      "person": {
        "id": 1,
        "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
        "firstName": "John",
        "lastName": "Smith",
        "email": "john.smith@example.com",
        "dateOfBirth": "28-04-1977",
        "bankAccountOwnerName": "John Smith",
        "financialAddress": "Wilhelmstraße 12",
        "financialModality": "Bank Account",
        "iban": "DE12 3456 7812 3456 7890",
        "bankName": "Golden Bank"
      },
      "packages": [
        {
          "id": 1,
          "name": "UBI",
          "description": "Universal basic income"
        }
      ]
    },
    "relative": {
      "id": 2,
      "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
      "firstName": "Emma",
      "lastName": "Smith",
      "email": "emma.smith@example.com",
      "dateOfBirth": "28-04-1977",
      "bankAccountOwnerName": "Emma Smith",
      "financialAddress": "Wilhelmstraße 12",
      "financialModality": "Bank Account",
      "iban": "DE12 3456 7812 3456 7890",
      "bankName": "Golden Bank"
    },
    "relationshipType": "WIFE"
  }
]
```

### Get by id

`/api/v1/household-information/{id}`

Response body

```json
  {
  "id": 1,
  "candidate": {
    "id": 1,
    "person": {
      "id": 1,
      "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
      "firstName": "John",
      "lastName": "Smith",
      "email": "john.smith@example.com",
      "dateOfBirth": "28-04-1977",
      "bankAccountOwnerName": "John Smith",
      "financialAddress": "Wilhelmstraße 12",
      "financialModality": "Bank Account",
      "iban": "DE12 3456 7812 3456 7890",
      "bankName": "Golden Bank"
    },
    "packages": [
      {
        "id": 1,
        "name": "UBI",
        "description": "Universal basic income"
      }
    ]
  },
  "relative": {
    "id": 2,
    "foundationalId": "9b237f8a-4dc2-4438-af0d-5f01c469b302",
    "firstName": "Emma",
    "lastName": "Smith",
    "email": "emma.smith@example.com",
    "dateOfBirth": "28-04-1977",
    "bankAccountOwnerName": "Emma Smith",
    "financialAddress": "Wilhelmstraße 12",
    "financialModality": "Bank Account",
    "iban": "DE12 3456 7812 3456 7890",
    "bankName": "Golden Bank"
  },
  "relationshipType": "WIFE"
}
```