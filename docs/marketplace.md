# Marketplace

Marketplace include [implementation](./../marketplace/blocks) of building blocks and [use cases](./../marketplace/use-cases).

## USCT use case deployment guide
Resource expectation:
* ~ 32 GB RAM 

### Generate passwords:

 * open-imis -> db -> dbService -> postgresPassword 
 * open-imis -> db -> dbService -> password 
 * open-imis -> db -> secret -> password 
 * open-imis -> backend -> deployment -> dbPassword 
 * consentbb -> keycloak 
 * consentbb -> api -> configuration -> User -> password 

### Build helm charts

` helm dependency update ./usct-full/`

` helm dependency build ./usct-full/`

` helm upgrade --install usct ./usct-full/ --create-namespace --namespace usct`

### Optional resources

`kubectl apply -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/main/example/prometheus-operator-crd/monitoring.coreos.com_servicemonitors.yaml`

`helm repo add metrics-server https://kubernetes-sigs.github.io/metrics-server/`

`helm upgrade --install metrics-server metrics-server/metrics-server`

### Payment hub
[Guide](main.md#post-deployment-steps).


