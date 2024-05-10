# Marketplace

Marketplace include [implementation](./../marketplace/blocks) of building blocks and [use cases](./../marketplace/use-cases).

## USCT use case deployment guide

### Generate passwords:

 * consentbb -> keycloak 
 * consentbb -> api -> configuration -> User -> password 

### Build helm charts

` helm dependency update ./usct-full/`

` helm dependency build ./usct-full/`

` helm upgrade --install usct ./usct-full/ --create-namespace --namespace usct`

### Payment hub

