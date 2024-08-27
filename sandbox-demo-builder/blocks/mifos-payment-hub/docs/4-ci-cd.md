# CI/CD instructions

## Environment variables

* AWS_CLUSTER_NAME = Kubernetes cluster name, e.g. "Govstack-sandbox-cluster-dev"
* AWS_DEFAULT_REGION = eu-central-1
* CHART_NAMESPACE = paymentub

## Deployment
Deployment process of installing Paymenthub and Fineract in EKS cluster.

### Circle CI Deploy Workflow:

To run follow those steps:

1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "deploy_allowed" and set value to "true"
5. Then trigger the pipeline

## Testing
Deployment process covered with a tests. They are checking:
* if all pods (deployment and statefulset) are up and run in 60 seconds.
* availabillity of used APIs.

### Circle CI Test Workflow:

To run follow those steps:

1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "test_allowed" and set value to "true"
5. Then trigger the pipeline
