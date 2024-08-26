# Digital-Registries Emulator
Digital-Registries builidng block Emulator implementation


## Concept & Requirements
[Requirements & Concept](https://govstack-global.atlassian.net/wiki/spaces/DEMO/pages/258801665/Building+Block+EMULATOR)


## Supported endpoints

Implemented endpoints based on Govstack Specification: [Link](https://govstack.gitbook.io/bb-digital-registries/8-service-apis)

## Currently emulated endpoints

### Benefit package
| Endpoint | METHOD | URL                                     |
| :------- | :----- | :-------------------------------------- |
| List     | GET    | /api/v1/data/bpkg/1.0 (only basic list) |
### Person
| Endpoint | METHOD | URL                                     |
| :------- | :----- | :-------------------------------------- |
| List     | GET    | /api/v1/data/prsn/1.0 (only basic list) |
| Read     | POST   | /api/v1/data/prsn/1.0/read              |
| Create   | POST   | /api/v1//data/prsn/1.0/create-entries   |
| Delete   | DELETE | /api/v1/data/prsn/1.0/{id}/delete       |



For access refer to Swagger UI in [URLs](./url.md)

## Documentations
* [Docker](./docker.md)
* [URLs](./url.md)
* [ENV-VARs](./env-vars.md)
* [DB Migrations](db-migrations.md)