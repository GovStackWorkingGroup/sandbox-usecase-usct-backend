# Mock-SRIS
This is a driver backend application for 
[Unconditional Social Cash Transfer](https://github.com/GovStackWorkingGroup/product-use-cases/blob/main/product-use-case/inst-1-unconditional-social-cash-transfer.md)
(USCT) use case.

## Test endpoints
The repository has a test endpoint `/emulator-health` to check the connection to the [information system](https://docs.x-road.global/Architecture/arc-g_x-road_arhitecture.html#23-information-system)
([payment-emulator](https://github.com/GovStackWorkingGroup/sandbox-bb-payments/tree/main/emulator/docs)) through the
[information mediator](https://github.com/GovStackWorkingGroup/sandbox-bb-information-mediator/blob/main/information-mediator/docs/main.md).

## CI/CD

Pipeline variables:
* AWS_RESOURCE_NAME_PREFIX = mock-sris/dev-app
* AWS_CLUSTER_NAME = Kubernetes cluster name, e.g. "Govstack-sandbox-cluster-dev"
* AWS_ACCOUNT = 463471358064 (Sandbox Dev)
* AWS_ROLE = CircleCIRole
* CHART_NAMESPACE = `mock-sris`
* AWS_DEFAULT_REGION = eu-central-1

## Useful commands

* `helm upgrade --install mock-sris ./helm/ --create-namespace --namespace mock-sris` 
* `helm install --debug --dry-run mock-sris ./helm/ --create-namespace --namespace mock-sris`
