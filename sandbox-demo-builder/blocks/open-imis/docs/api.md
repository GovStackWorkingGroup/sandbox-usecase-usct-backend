# API

## Demo API

`https://demo.openimis.org/api/api_fhir_r4/login/`

Related [Confluence page](https://govstack-global.atlassian.net/wiki/spaces/DEMO/pages/179601480/Registration#OpenIMIS-resources)

## User login

User/Civil servant logs in. To create new record the user must have certain role

POST `/api_fhir_r4/login/`

```json
{
  "username": "",
  "password": ""
}
```
Credentials can be found [here](https://openimis.atlassian.net/wiki/spaces/OP/pages/635502598/Demo+Script) and [here](https://openimis.atlassian.net/wiki/spaces/OP/pages/3219128428/Sandbox+Landscape#Demo-Line).

Response

```json
{
  "token": "{token}",
  "exp": 1679054141
}
```


## Provide Citizen personal data
Civil servant opens the registration form to fill the beneficiary registration form. 
Civil Servant enters citizens personal ID. Citizens personal data is pulled from the CR and form filled automatically

POST `/api_fhir_r4/Patient/`

Request
```json
{
  "resourceType": "Patient",
  "extension": [
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
      "valueBoolean": false
    },
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
      "valueBoolean": false
    },
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
      "valueReference": {
        "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
        "type": "Group",
        "identifier": {
          "type": {
            "coding": [
              {
                "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                "code": "UUID"
              }
            ]
          },
          "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
        }
      }
    }
  ],
  "identifier": [
    {
      "type": {
        "coding": [
          {
            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
            "code": "Code"
          }
        ]
      },
      "value": "111111129"
    }
  ],
  "name": [
    {
      "use": "usual",
      "family": "Manth",
      "given": [
        "Aby"
      ]
    }
  ],
  "gender": "female",
  "birthDate": "2001-05-17",
  "address": [
    {
      "extension": [
        {
          "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
          "valueString": "Achi"
        },
        {
          "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
          "valueReference": {
            "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
            }
          }
        }
      ],
      "use": "home",
      "type": "physical",
      "text": "Jetset zone 85",
      "city": "Rachla",
      "district": "Rapta",
      "state": "Ultha"
    }
  ]
}
```

Response
```json
{
  "resourceType": "Patient",
  "id": "3ea457b2-0c0f-487a-a856-d5f2ffd0ae80",
  "extension": [
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
      "valueBoolean": false
    },
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
      "valueBoolean": false
    },
    {
      "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
      "valueReference": {
        "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
        "type": "Group",
        "identifier": {
          "type": {
            "coding": [
              {
                "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                "code": "UUID"
              }
            ]
          },
          "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
        }
      }
    }
  ],
  "identifier": [
    {
      "type": {
        "coding": [
          {
            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
            "code": "UUID"
          }
        ]
      },
      "value": "3ea457b2-0c0f-487a-a856-d5f2ffd0ae80"
    },
    {
      "type": {
        "coding": [
          {
            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
            "code": "Code"
          }
        ]
      },
      "value": "111111129"
    }
  ],
  "name": [
    {
      "use": "usual",
      "family": "Manth",
      "given": [
        "Aby"
      ]
    }
  ],
  "gender": "female",
  "birthDate": "2001-05-17",
  "address": [
    {
      "extension": [
        {
          "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
          "valueString": "Achi"
        },
        {
          "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
          "valueReference": {
            "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
            }
          }
        }
      ],
      "use": "temp",
      "type": "physical",
      "text": "Jetset zone 85",
      "city": "Rachla",
      "district": "Rapta",
      "state": "Ultha"
    }
  ]
}
```

GET `/api_fhir_r4/Patient/`

```json
{
  "resourceType": "Bundle",
  "type": "searchset",
  "total": 32,
  "link": [
    {
      "relation": "self",
      "url": "http%3A%2F%2Flocalhost%3A8001%2Fapi_fhir_r4%2FPatient%2F"
    },
    {
      "relation": "next",
      "url": "http%3A%2F%2Flocalhost%3A8001%2Fapi_fhir_r4%2FPatient%2F%3Fpage-offset%3D2"
    }
  ],
  "entry": [
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/23cf1d3c-d07e-4ac8-a966-87ed502a454e",
      "resource": {
        "resourceType": "Patient",
        "id": "23cf1d3c-d07e-4ac8-a966-87ed502a454e",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "23cf1d3c-d07e-4ac8-a966-87ed502a454e"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111117"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Aby"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "2001-05-17",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111117_E00001_20180327_0.0_0.0.jpg",
            "title": "111111117_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/fe2048e7-2810-4af8-aeaf-69d567111a0b",
      "resource": {
        "resourceType": "Patient",
        "id": "fe2048e7-2810-4af8-aeaf-69d567111a0b",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "fe2048e7-2810-4af8-aeaf-69d567111a0b"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111118"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Rennie"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "1976-04-23",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111118_E00001_20180327_0.0_0.0.jpg",
            "title": "111111118_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "8",
                    "display": "Spouse"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Manth",
              "given": [
                "Rennie"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/1e94a0cf-75ab-4d1b-a76a-d5cace35835f",
      "resource": {
        "resourceType": "Patient",
        "id": "1e94a0cf-75ab-4d1b-a76a-d5cace35835f",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": true
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "1e94a0cf-75ab-4d1b-a76a-d5cace35835f"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111119"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Roger"
            ]
          }
        ],
        "gender": "male",
        "birthDate": "1970-01-04",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111119_E00001_20180327_0.0_0.0.jpg",
            "title": "111111119_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/377248c9-9893-4543-8a0a-14aa564d9eeb",
      "resource": {
        "resourceType": "Patient",
        "id": "377248c9-9893-4543-8a0a-14aa564d9eeb",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "377248c9-9893-4543-8a0a-14aa564d9eeb"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111116"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Gayle"
            ]
          }
        ],
        "gender": "male",
        "birthDate": "1953-05-12",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111116_E00001_20180327_0.0_0.0.jpg",
            "title": "111111116_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "5",
                    "display": "Grand parents"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Manth",
              "given": [
                "Gayle"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/cee1c408-e3ae-41b5-b2f7-f7be5882f854",
      "resource": {
        "resourceType": "Patient",
        "id": "cee1c408-e3ae-41b5-b2f7-f7be5882f854",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "cee1c408-e3ae-41b5-b2f7-f7be5882f854"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111114"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Ramy"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "2009-09-25",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111114_E00001_20180327_0.0_0.0.jpg",
            "title": "111111114_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "4",
                    "display": "Son/Daughter"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Manth",
              "given": [
                "Ramy"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/a1a121c0-605f-41b0-90fd-25ac63b69ede",
      "resource": {
        "resourceType": "Patient",
        "id": "a1a121c0-605f-41b0-90fd-25ac63b69ede",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "a1a121c0-605f-41b0-90fd-25ac63b69ede"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "111111115"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Manth",
            "given": [
              "Gabbie"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "1980-08-17",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Jetset zone 85",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//111111115_E00001_20180327_0.0_0.0.jpg",
            "title": "111111115_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "5",
                    "display": "Grand parents"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Manth",
              "given": [
                "Gabbie"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/0539afeb-21a1-4b3f-9478-23e6f41b0024",
      "resource": {
        "resourceType": "Patient",
        "id": "0539afeb-21a1-4b3f-9478-23e6f41b0024",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": true
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/822a8e55-2290-4df6-8882-1d702c2ffc8b",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "822a8e55-2290-4df6-8882-1d702c2ffc8b"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "0539afeb-21a1-4b3f-9478-23e6f41b0024"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "070707070"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Macintyre",
            "given": [
              "Joseph"
            ]
          }
        ],
        "gender": "male",
        "birthDate": "1950-07-12",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Ranchou road 21",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "maritalStatus": {
          "coding": [
            {
              "system": "http://hl7.org/fhir/valueset-marital-status.html",
              "code": "M",
              "display": "Married"
            }
          ]
        },
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//070707070_E00001_20180327_0.0_0.0.jpg",
            "title": "070707070_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/4d908777-50ff-44c3-81f2-48f27517c798",
      "resource": {
        "resourceType": "Patient",
        "id": "4d908777-50ff-44c3-81f2-48f27517c798",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/822a8e55-2290-4df6-8882-1d702c2ffc8b",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "822a8e55-2290-4df6-8882-1d702c2ffc8b"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "4d908777-50ff-44c3-81f2-48f27517c798"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "070707055"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Macintyre",
            "given": [
              "Jet"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "2005-10-16",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Ranchou road 21",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//070707055_E00001_20180327_0.0_0.0.jpg",
            "title": "070707055_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "7",
                    "display": "Others"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Macintyre",
              "given": [
                "Jet"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/d8393487-c3aa-4d3a-b56f-82f9b3b47a46",
      "resource": {
        "resourceType": "Patient",
        "id": "d8393487-c3aa-4d3a-b56f-82f9b3b47a46",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/822a8e55-2290-4df6-8882-1d702c2ffc8b",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "822a8e55-2290-4df6-8882-1d702c2ffc8b"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "d8393487-c3aa-4d3a-b56f-82f9b3b47a46"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "070707066"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Macintyre",
            "given": [
              "Abu"
            ]
          }
        ],
        "gender": "male",
        "birthDate": "1973-11-21",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Ranchou road 21",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//070707066_E00001_20180327_0.0_0.0.jpg",
            "title": "070707066_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "7",
                    "display": "Others"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Macintyre",
              "given": [
                "Abu"
              ]
            }
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8001/api_fhir_r4/Patient/7b00e6ec-e0f5-4d07-a8e0-ec00a4160e73",
      "resource": {
        "resourceType": "Patient",
        "id": "7b00e6ec-e0f5-4d07-a8e0-ec00a4160e73",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-is-head",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-card-issued",
            "valueBoolean": false
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/patient-group-reference",
            "valueReference": {
              "reference": "Group/822a8e55-2290-4df6-8882-1d702c2ffc8b",
              "type": "Group",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "822a8e55-2290-4df6-8882-1d702c2ffc8b"
              }
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "7b00e6ec-e0f5-4d07-a8e0-ec00a4160e73"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "070707081"
          }
        ],
        "name": [
          {
            "use": "usual",
            "family": "Macintyre",
            "given": [
              "Jane"
            ]
          }
        ],
        "gender": "female",
        "birthDate": "1952-05-07",
        "address": [
          {
            "extension": [
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-municipality",
                "valueString": "Achi"
              },
              {
                "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/address-location-reference",
                "valueReference": {
                  "reference": "Location/8ed4eb0d-61ae-4022-8b4c-3076a619f957",
                  "type": "Location",
                  "identifier": {
                    "type": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                          "code": "UUID"
                        }
                      ]
                    },
                    "value": "8ed4eb0d-61ae-4022-8b4c-3076a619f957"
                  }
                }
              }
            ],
            "use": "home",
            "type": "physical",
            "text": "Ranchou road 21",
            "city": "Rachla",
            "district": "Rapta",
            "state": "Ultha"
          }
        ],
        "maritalStatus": {
          "coding": [
            {
              "system": "http://hl7.org/fhir/valueset-marital-status.html",
              "code": "M",
              "display": "Married"
            }
          ]
        },
        "photo": [
          {
            "contentType": "jpg",
            "url": "http://localhost/photo/Images/Updated//070707081_E00001_20180327_0.0_0.0.jpg",
            "title": "070707081_E00001_20180327_0.0_0.0.jpg",
            "creation": "2018-03-27"
          }
        ],
        "contact": [
          {
            "relationship": [
              {
                "coding": [
                  {
                    "system": "CodeSystem/patient-contact-relationship",
                    "code": "8",
                    "display": "Spouse"
                  }
                ]
              }
            ],
            "name": {
              "use": "usual",
              "family": "Macintyre",
              "given": [
                "Jane"
              ]
            }
          }
        ]
      }
    }
  ]
}
```


## Provide Benefit program details (Product details)

GET `/api_fhir_r4/InsurancePlan`

```json
{
  "resourceType": "Bundle",
  "type": "searchset",
  "total": 4,
  "link": [
    {
      "relation": "self",
      "url": "http%3A%2F%2Flocalhost%3A8002%2Fapi_fhir_r4%2FInsurancePlan%2F"
    }
  ],
  "entry": [
    {
      "fullUrl": "http://localhost:8002/api_fhir_r4/InsurancePlan/cf250507-a9ec-4d45-bb57-7f6bda8d696a",
      "resource": {
        "resourceType": "InsurancePlan",
        "id": "cf250507-a9ec-4d45-bb57-7f6bda8d696a",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-max-installments",
            "valueUnsignedInt": 3
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-start_cycle",
            "valueString": "01-06"
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-start_cycle",
            "valueString": "01-11"
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "cf250507-a9ec-4d45-bb57-7f6bda8d696a"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "FCTA0001"
          }
        ],
        "status": "active",
        "type": [
          {
            "coding": [
              {
                "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                "code": "medical",
                "display": "Medical"
              }
            ]
          }
        ],
        "name": "Fixed Cycle Cover Tahida",
        "period": {
          "start": "2017-01-01T00:00:00",
          "end": "2030-12-31T00:00:00"
        },
        "coverageArea": [
          {
            "reference": "Location/68753566-9d2e-4cec-936e-4c6bf1968c0d",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "68753566-9d2e-4cec-936e-4c6bf1968c0d"
            }
          }
        ],
        "coverage": [
          {
            "type": {
              "coding": [
                {
                  "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                  "code": "medical"
                }
              ]
            },
            "benefit": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                      "code": "medical"
                    }
                  ]
                },
                "limit": [
                  {
                    "value": {
                      "value": 12.0,
                      "unit": "month"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "period",
                          "display": "Period"
                        }
                      ]
                    }
                  },
                  {
                    "value": {
                      "value": 9999.0,
                      "unit": "member"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "memberCount",
                          "display": "Member Count"
                        }
                      ]
                    }
                  }
                ]
              }
            ]
          }
        ],
        "plan": [
          {
            "generalCost": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "lumpsum",
                      "display": "Lumpsum"
                    }
                  ]
                },
                "groupSize": 6,
                "cost": {
                  "value": 0.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "premiumAdult",
                      "display": "Premium Adult"
                    }
                  ]
                },
                "cost": {
                  "value": 4000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "premiumChild",
                      "display": "Premium Child"
                    }
                  ]
                },
                "cost": {
                  "value": 4000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "registrationLumpsum",
                      "display": "Registration Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 1000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "generalAssemblyLumpSum",
                      "display": "General Assembly Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 1000.0,
                  "currency": "$"
                }
              }
            ]
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8002/api_fhir_r4/InsurancePlan/df7a9ed8-f34e-439a-80df-5c187083d542",
      "resource": {
        "resourceType": "InsurancePlan",
        "id": "df7a9ed8-f34e-439a-80df-5c187083d542",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-max-installments",
            "valueUnsignedInt": 3
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-start_cycle",
            "valueString": "01-06"
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-start_cycle",
            "valueString": "01-11"
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "df7a9ed8-f34e-439a-80df-5c187083d542"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "FCUL0001"
          }
        ],
        "status": "active",
        "type": [
          {
            "coding": [
              {
                "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                "code": "medical",
                "display": "Medical"
              }
            ]
          }
        ],
        "name": "Fixed Cycle Cover Ultha",
        "period": {
          "start": "2017-01-01T00:00:00",
          "end": "2030-12-31T00:00:00"
        },
        "coverageArea": [
          {
            "reference": "Location/75250515-40d7-4c77-bafe-a2c65ffc5a72",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "75250515-40d7-4c77-bafe-a2c65ffc5a72"
            }
          }
        ],
        "coverage": [
          {
            "type": {
              "coding": [
                {
                  "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                  "code": "medical"
                }
              ]
            },
            "benefit": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                      "code": "medical"
                    }
                  ]
                },
                "limit": [
                  {
                    "value": {
                      "value": 12.0,
                      "unit": "month"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "period",
                          "display": "Period"
                        }
                      ]
                    }
                  },
                  {
                    "value": {
                      "value": 9999.0,
                      "unit": "member"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "memberCount",
                          "display": "Member Count"
                        }
                      ]
                    }
                  }
                ]
              }
            ]
          }
        ],
        "plan": [
          {
            "generalCost": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "lumpsum",
                      "display": "Lumpsum"
                    }
                  ]
                },
                "groupSize": 6,
                "cost": {
                  "value": 0.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "premiumAdult",
                      "display": "Premium Adult"
                    }
                  ]
                },
                "cost": {
                  "value": 4000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "premiumChild",
                      "display": "Premium Child"
                    }
                  ]
                },
                "cost": {
                  "value": 4000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "registrationLumpsum",
                      "display": "Registration Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 1000.0,
                  "currency": "$"
                }
              },
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "generalAssemblyLumpSum",
                      "display": "General Assembly Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 1000.0,
                  "currency": "$"
                }
              }
            ]
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8002/api_fhir_r4/InsurancePlan/e2d028c9-db61-4508-8cfa-252f73dc7fbc",
      "resource": {
        "resourceType": "InsurancePlan",
        "id": "e2d028c9-db61-4508-8cfa-252f73dc7fbc",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-max-installments",
            "valueUnsignedInt": 1
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "e2d028c9-db61-4508-8cfa-252f73dc7fbc"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "BCTA0001"
          }
        ],
        "status": "active",
        "type": [
          {
            "coding": [
              {
                "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                "code": "medical",
                "display": "Medical"
              }
            ]
          }
        ],
        "name": "Basic Cover Tahida",
        "period": {
          "start": "2017-01-01T00:00:00",
          "end": "2030-12-31T00:00:00"
        },
        "coverageArea": [
          {
            "reference": "Location/68753566-9d2e-4cec-936e-4c6bf1968c0d",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "68753566-9d2e-4cec-936e-4c6bf1968c0d"
            }
          }
        ],
        "coverage": [
          {
            "type": {
              "coding": [
                {
                  "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                  "code": "medical"
                }
              ]
            },
            "benefit": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                      "code": "medical"
                    }
                  ]
                },
                "limit": [
                  {
                    "value": {
                      "value": 12.0,
                      "unit": "month"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "period",
                          "display": "Period"
                        }
                      ]
                    }
                  },
                  {
                    "value": {
                      "value": 6.0,
                      "unit": "member"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "memberCount",
                          "display": "Member Count"
                        }
                      ]
                    }
                  }
                ]
              }
            ]
          }
        ],
        "plan": [
          {
            "generalCost": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "lumpsum",
                      "display": "Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 10000.0,
                  "currency": "$"
                }
              }
            ]
          }
        ]
      }
    },
    {
      "fullUrl": "http://localhost:8002/api_fhir_r4/InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
      "resource": {
        "resourceType": "InsurancePlan",
        "id": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
        "extension": [
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-conversion",
            "valueReference": {
              "reference": "InsurancePlan/df7a9ed8-f34e-439a-80df-5c187083d542",
              "type": "InsurancePlan",
              "identifier": {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                      "code": "UUID"
                    }
                  ]
                },
                "value": "df7a9ed8-f34e-439a-80df-5c187083d542"
              },
              "display": "FCUL0001"
            }
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-max-installments",
            "valueUnsignedInt": 1
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          },
          {
            "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/insurance-plan-period",
            "valueQuantity": {
              "value": 0.0,
              "unit": "months"
            }
          }
        ],
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "Code"
                }
              ]
            },
            "value": "BCUL0001"
          }
        ],
        "status": "active",
        "type": [
          {
            "coding": [
              {
                "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                "code": "medical",
                "display": "Medical"
              }
            ]
          }
        ],
        "name": "Basic Cover Ultha",
        "period": {
          "start": "2017-01-01T00:00:00",
          "end": "2030-12-31T00:00:00"
        },
        "coverageArea": [
          {
            "reference": "Location/75250515-40d7-4c77-bafe-a2c65ffc5a72",
            "type": "Location",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "75250515-40d7-4c77-bafe-a2c65ffc5a72"
            }
          }
        ],
        "coverage": [
          {
            "type": {
              "coding": [
                {
                  "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                  "code": "medical"
                }
              ]
            },
            "benefit": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "http://terminology.hl7.org/CodeSystem/insurance-plan-type",
                      "code": "medical"
                    }
                  ]
                },
                "limit": [
                  {
                    "value": {
                      "value": 12.0,
                      "unit": "month"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "period",
                          "display": "Period"
                        }
                      ]
                    }
                  },
                  {
                    "value": {
                      "value": 6.0,
                      "unit": "member"
                    },
                    "code": {
                      "coding": [
                        {
                          "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-coverage-benefit-limit",
                          "code": "memberCount",
                          "display": "Member Count"
                        }
                      ]
                    }
                  }
                ]
              }
            ]
          }
        ],
        "plan": [
          {
            "generalCost": [
              {
                "type": {
                  "coding": [
                    {
                      "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/insurance-plan-general-cost-type",
                      "code": "lumpsum",
                      "display": "Lumpsum"
                    }
                  ]
                },
                "cost": {
                  "value": 10000.0,
                  "currency": "$"
                }
              }
            ]
          }
        ]
      }
    }
  ]
}
```

## Request Beneficiary enrollment

Civil Servant enrolls beneficiary

New beneficiary record is created and related to suitable benefit program (product) to the OpenIMIS

GET `/api_fhir_r4/Contract/`

```json
{
  "resourceType": "Bundle",
  "type": "searchset",
  "total": 21,
  "link": [
    {
      "relation": "self",
      "url": "http%3A%2F%2Flocalhost%3A8001%2Fapi_fhir_r4%2FContract%2F"
    },
    {
      "relation": "next",
      "url": "http%3A%2F%2Flocalhost%3A8001%2Fapi_fhir_r4%2FContract%2F%3Fpage-offset%3D2"
    }
  ],
  "entry": [
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "a3df066b-3e23-429a-92ad-cd1ef46daa2f"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "1"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/822a8e55-2290-4df6-8882-1d702c2ffc8b",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "822a8e55-2290-4df6-8882-1d702c2ffc8b"
            },
            "display": "Macintyre"
          }
        ],
        "author": {
          "reference": "Practitioner/4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99"
          },
          "display": "E00005"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/0539afeb-21a1-4b3f-9478-23e6f41b0024",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "0539afeb-21a1-4b3f-9478-23e6f41b0024"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "555"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "B",
                              "display": "Bank transfer"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/0539afeb-21a1-4b3f-9478-23e6f41b0024",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "0539afeb-21a1-4b3f-9478-23e6f41b0024"
                    },
                    "display": "070707070"
                  },
                  {
                    "reference": "Patient/7b00e6ec-e0f5-4d07-a8e0-ec00a4160e73",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "7b00e6ec-e0f5-4d07-a8e0-ec00a4160e73"
                    },
                    "display": "070707081"
                  },
                  {
                    "reference": "Patient/f13100e9-9ab3-47b8-8579-150beb56ffad",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "f13100e9-9ab3-47b8-8579-150beb56ffad"
                    },
                    "display": "070707092"
                  },
                  {
                    "reference": "Patient/d8393487-c3aa-4d3a-b56f-82f9b3b47a46",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "d8393487-c3aa-4d3a-b56f-82f9b3b47a46"
                    },
                    "display": "070707066"
                  },
                  {
                    "reference": "Patient/4d908777-50ff-44c3-81f2-48f27517c798",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "4d908777-50ff-44c3-81f2-48f27517c798"
                    },
                    "display": "070707055"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "3bddc83f-ae4a-45ff-a940-d25fb19a7a05"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "3"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/c8e83c86-5868-479a-8c30-b41d16c77cc3",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "c8e83c86-5868-479a-8c30-b41d16c77cc3"
            },
            "display": "Manth"
          }
        ],
        "author": {
          "reference": "Practitioner/4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99"
          },
          "display": "E00005"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/1e94a0cf-75ab-4d1b-a76a-d5cace35835f",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "1e94a0cf-75ab-4d1b-a76a-d5cace35835f"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE36"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/1e94a0cf-75ab-4d1b-a76a-d5cace35835f",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "1e94a0cf-75ab-4d1b-a76a-d5cace35835f"
                    },
                    "display": "111111119"
                  },
                  {
                    "reference": "Patient/fe2048e7-2810-4af8-aeaf-69d567111a0b",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "fe2048e7-2810-4af8-aeaf-69d567111a0b"
                    },
                    "display": "111111118"
                  },
                  {
                    "reference": "Patient/23cf1d3c-d07e-4ac8-a966-87ed502a454e",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "23cf1d3c-d07e-4ac8-a966-87ed502a454e"
                    },
                    "display": "111111117"
                  },
                  {
                    "reference": "Patient/377248c9-9893-4543-8a0a-14aa564d9eeb",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "377248c9-9893-4543-8a0a-14aa564d9eeb"
                    },
                    "display": "111111116"
                  },
                  {
                    "reference": "Patient/a1a121c0-605f-41b0-90fd-25ac63b69ede",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "a1a121c0-605f-41b0-90fd-25ac63b69ede"
                    },
                    "display": "111111115"
                  },
                  {
                    "reference": "Patient/cee1c408-e3ae-41b5-b2f7-f7be5882f854",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "cee1c408-e3ae-41b5-b2f7-f7be5882f854"
                    },
                    "display": "111111114"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "f31aba41-f5a8-4fd9-b77d-b6c0b0f97946"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "5"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/f6a0b402-0dc0-436e-a7bb-ec65dd4f011f",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "f6a0b402-0dc0-436e-a7bb-ec65dd4f011f"
            },
            "display": "Yellow"
          }
        ],
        "author": {
          "reference": "Practitioner/9c12ceb9-968e-4752-94d9-7420c7bd580f",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "9c12ceb9-968e-4752-94d9-7420c7bd580f"
          },
          "display": "E00002"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/8572a782-2fde-44e7-af10-a5351ebf13bd",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "8572a782-2fde-44e7-af10-a5351ebf13bd"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE184"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/8572a782-2fde-44e7-af10-a5351ebf13bd",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "8572a782-2fde-44e7-af10-a5351ebf13bd"
                    },
                    "display": "100000001"
                  },
                  {
                    "reference": "Patient/8f6598a8-a383-48d1-bcfa-539ebc211420",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "8f6598a8-a383-48d1-bcfa-539ebc211420"
                    },
                    "display": "100000002"
                  },
                  {
                    "reference": "Patient/51e882fd-8e01-475e-b332-01f03151a201",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "51e882fd-8e01-475e-b332-01f03151a201"
                    },
                    "display": "100000003"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "37272dd4-a161-45ce-a9e5-0e7242976c37"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "7"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/c0489ff1-ddd9-442d-ad3b-ba6c59ac60cf",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "c0489ff1-ddd9-442d-ad3b-ba6c59ac60cf"
            },
            "display": "Ramula"
          }
        ],
        "author": {
          "reference": "Practitioner/671ef662-cd56-4740-8d2d-55cb4f451418",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "671ef662-cd56-4740-8d2d-55cb4f451418"
          },
          "display": "E00001"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/a4b00fdf-f978-4b64-8337-599358f96e1e",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "a4b00fdf-f978-4b64-8337-599358f96e1e"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE3423"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/a4b00fdf-f978-4b64-8337-599358f96e1e",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "a4b00fdf-f978-4b64-8337-599358f96e1e"
                    },
                    "display": "110000001"
                  },
                  {
                    "reference": "Patient/a0e2c5c6-5c5b-499f-aec7-725d819c0d14",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "a0e2c5c6-5c5b-499f-aec7-725d819c0d14"
                    },
                    "display": "110000002"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "3608f21b-0720-43d9-bb92-0d27ec1b59e9"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "9"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/a8e4ded0-eab3-47d5-bdc6-daeeea76f9fc",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "a8e4ded0-eab3-47d5-bdc6-daeeea76f9fc"
            },
            "display": "Badman"
          }
        ],
        "author": {
          "reference": "Practitioner/ceab7e5e-6335-41ec-a454-8e1dc644170e",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "ceab7e5e-6335-41ec-a454-8e1dc644170e"
          },
          "display": "E00003"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/cfff1594-a9b7-46a8-a253-8ead814d7e9d",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "cfff1594-a9b7-46a8-a253-8ead814d7e9d"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RF231"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/cfff1594-a9b7-46a8-a253-8ead814d7e9d",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "cfff1594-a9b7-46a8-a253-8ead814d7e9d"
                    },
                    "display": "120000001"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "1f4f4aa0-ff52-4cac-8e2a-14d8207b301c"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "11"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/4f42a66b-10ba-41c7-9b44-0af6dccddd07",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "4f42a66b-10ba-41c7-9b44-0af6dccddd07"
            },
            "display": "Bonjorna"
          }
        ],
        "author": {
          "reference": "Practitioner/462fde35-2cac-4315-a670-142a18f0c4eb",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "462fde35-2cac-4315-a670-142a18f0c4eb"
          },
          "display": "E00013"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/0df21cd3-dd98-4634-8942-5d566e6ec111",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "0df21cd3-dd98-4634-8942-5d566e6ec111"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE453"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/0df21cd3-dd98-4634-8942-5d566e6ec111",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "0df21cd3-dd98-4634-8942-5d566e6ec111"
                    },
                    "display": "13000001"
                  },
                  {
                    "reference": "Patient/168e83d7-1570-4d8b-b99f-6c7b4f686567",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "168e83d7-1570-4d8b-b99f-6c7b4f686567"
                    },
                    "display": "13000002"
                  },
                  {
                    "reference": "Patient/5f10aca0-ca78-4ae6-861e-627cfd5c75e8",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "5f10aca0-ca78-4ae6-861e-627cfd5c75e8"
                    },
                    "display": "13000003"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "5f17014f-2694-4afe-8971-1caa4cf49919"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "12"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/7604e094-430a-4b0b-a498-76c1307f2f01",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "7604e094-430a-4b0b-a498-76c1307f2f01"
            },
            "display": "Labunda"
          }
        ],
        "author": {
          "reference": "Practitioner/462fde35-2cac-4315-a670-142a18f0c4eb",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "462fde35-2cac-4315-a670-142a18f0c4eb"
          },
          "display": "E00013"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/619d324c-4495-4ec7-86ab-b7f5277027b1",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "619d324c-4495-4ec7-86ab-b7f5277027b1"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE9237"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/619d324c-4495-4ec7-86ab-b7f5277027b1",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "619d324c-4495-4ec7-86ab-b7f5277027b1"
                    },
                    "display": "140000001"
                  },
                  {
                    "reference": "Patient/e690894b-47ae-4171-9ddd-6a4e4c15744a",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "e690894b-47ae-4171-9ddd-6a4e4c15744a"
                    },
                    "display": "140000002"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "23fb7f2e-5d7b-475d-b83c-c6afd682254e"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "15"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/2f1eeafd-98b8-4ea8-b8bf-688913ec5eb7",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "2f1eeafd-98b8-4ea8-b8bf-688913ec5eb7"
            },
            "display": "Lathuro"
          }
        ],
        "author": {
          "reference": "Practitioner/2b38782c-e45e-4f20-b3c2-5d8eb1e188e5",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "2b38782c-e45e-4f20-b3c2-5d8eb1e188e5"
          },
          "display": "E00006"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/5b3f8592-6014-4dd2-924a-0cdc17830609",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "5b3f8592-6014-4dd2-924a-0cdc17830609"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE6283"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/5b3f8592-6014-4dd2-924a-0cdc17830609",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "5b3f8592-6014-4dd2-924a-0cdc17830609"
                    },
                    "display": "150000001"
                  },
                  {
                    "reference": "Patient/4b04b71d-4951-49a2-99c6-afeade6d903a",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "4b04b71d-4951-49a2-99c6-afeade6d903a"
                    },
                    "display": "150000002"
                  },
                  {
                    "reference": "Patient/230ca0e0-de09-4252-bc6c-5eaee5a79716",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "230ca0e0-de09-4252-bc6c-5eaee5a79716"
                    },
                    "display": "150000003"
                  },
                  {
                    "reference": "Patient/52748776-2a6f-4e84-a5e3-0967d80386c3",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "52748776-2a6f-4e84-a5e3-0967d80386c3"
                    },
                    "display": "150000004"
                  },
                  {
                    "reference": "Patient/de96afc0-c492-4b1a-985b-717c8d0cf356",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "de96afc0-c492-4b1a-985b-717c8d0cf356"
                    },
                    "display": "150000005"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "d1e2c353-ff86-4b81-9523-96229987bd1c"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "17"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/3d10113c-b8b1-4956-91bb-ea75feebed7e",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "3d10113c-b8b1-4956-91bb-ea75feebed7e"
            },
            "display": "Shan"
          }
        ],
        "author": {
          "reference": "Practitioner/2b38782c-e45e-4f20-b3c2-5d8eb1e188e5",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "2b38782c-e45e-4f20-b3c2-5d8eb1e188e5"
          },
          "display": "E00006"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/20df59b8-e34d-478a-96b0-6f0e35bf4468",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "20df59b8-e34d-478a-96b0-6f0e35bf4468"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE259"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/20df59b8-e34d-478a-96b0-6f0e35bf4468",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "20df59b8-e34d-478a-96b0-6f0e35bf4468"
                    },
                    "display": "160000001"
                  },
                  {
                    "reference": "Patient/17bf4c1c-8079-47a5-9566-38671f55c10e",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "17bf4c1c-8079-47a5-9566-38671f55c10e"
                    },
                    "display": "160000002"
                  },
                  {
                    "reference": "Patient/6c86ea44-17de-4c70-b553-b4a21a1ab4fd",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "6c86ea44-17de-4c70-b553-b4a21a1ab4fd"
                    },
                    "display": "160000003"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    {
      "resource": {
        "resourceType": "Contract",
        "identifier": [
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "6a2a7a42-6f4e-443c-b85f-bb046716cf56"
          },
          {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "ACSN"
                }
              ]
            },
            "value": "19"
          }
        ],
        "status": "Policy",
        "legalState": {
          "text": "Offered"
        },
        "subject": [
          {
            "reference": "Group/953351f9-bd1f-499c-82ab-e837aefcada7",
            "type": "Group",
            "identifier": {
              "type": {
                "coding": [
                  {
                    "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                    "code": "UUID"
                  }
                ]
              },
              "value": "953351f9-bd1f-499c-82ab-e837aefcada7"
            },
            "display": "Barumida"
          }
        ],
        "author": {
          "reference": "Practitioner/4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99",
          "type": "Practitioner",
          "identifier": {
            "type": {
              "coding": [
                {
                  "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                  "code": "UUID"
                }
              ]
            },
            "value": "4da8cbbb-8bd6-4e49-b25d-a34a76d4ce99"
          },
          "display": "E00005"
        },
        "scope": {
          "coding": [
            {
              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-scope",
              "code": "informal",
              "display": "Informal Sector"
            }
          ]
        },
        "term": [
          {
            "offer": {
              "party": [
                {
                  "reference": [
                    {
                      "reference": "Patient/44d64808-bca1-4043-89b1-aa045dcc0b04",
                      "type": "Patient",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "44d64808-bca1-4043-89b1-aa045dcc0b04"
                      }
                    }
                  ],
                  "role": {
                    "coding": [
                      {
                        "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-resource-party-role",
                        "code": "beneficiary",
                        "display": "Beneficiary"
                      }
                    ]
                  }
                }
              ]
            },
            "asset": [
              {
                "extension": [
                  {
                    "extension": [
                      {
                        "url": "payer",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-payer",
                              "code": "beneficiary",
                              "display": "Beneficiary"
                            }
                          ]
                        }
                      },
                      {
                        "url": "category",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-category",
                              "code": "C",
                              "display": "Contribution and Others"
                            }
                          ]
                        }
                      },
                      {
                        "url": "amount",
                        "valueMoney": {
                          "value": 10000.0,
                          "currency": "$"
                        }
                      },
                      {
                        "url": "receipt",
                        "valueString": "RE8973"
                      },
                      {
                        "url": "date",
                        "valueDate": "2019-08-20"
                      },
                      {
                        "url": "type",
                        "valueCodeableConcept": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/contract-premium-type",
                              "code": "C",
                              "display": "Cash"
                            }
                          ]
                        }
                      }
                    ],
                    "url": "https://openimis.github.io/openimis_fhir_r4_ig/StructureDefinition/contract-premium"
                  }
                ],
                "typeReference": [
                  {
                    "reference": "Patient/44d64808-bca1-4043-89b1-aa045dcc0b04",
                    "type": "Patient",
                    "identifier": {
                      "type": {
                        "coding": [
                          {
                            "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                            "code": "UUID"
                          }
                        ]
                      },
                      "value": "44d64808-bca1-4043-89b1-aa045dcc0b04"
                    },
                    "display": "170000001"
                  }
                ],
                "period": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "usePeriod": [
                  {
                    "start": "2019-08-20",
                    "end": "2020-08-19"
                  }
                ],
                "valuedItem": [
                  {
                    "entityReference": {
                      "reference": "InsurancePlan/9ad6e81d-ce42-43ba-aa2e-4ec3978352e8",
                      "type": "InsurancePlan",
                      "identifier": {
                        "type": {
                          "coding": [
                            {
                              "system": "https://openimis.github.io/openimis_fhir_r4_ig/CodeSystem/openimis-identifiers",
                              "code": "UUID"
                            }
                          ]
                        },
                        "value": "9ad6e81d-ce42-43ba-aa2e-4ec3978352e8"
                      },
                      "display": "BCUL0001"
                    },
                    "net": {
                      "value": 10000.0
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    }
  ]
}
```

## Notify payment BB of new payment request

GET `/api_fhir_r4/PaymentNotice/`

POST `/api_fhir_r4/PaymentNotice/`
