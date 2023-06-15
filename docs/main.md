# Mock-SRIS

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

