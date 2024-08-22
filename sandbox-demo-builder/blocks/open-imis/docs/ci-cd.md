# CI/CD

GovStack ORB setup [instruction](https://govstack-global.atlassian.net/wiki/spaces/GH/pages/191692823/ORB+setup+instruction
)

## Environment variables

* AWS_ACCOUNT = 463471358064 (Sandbox Dev)
* AWS_ROLE = CircleCIRole
* AWS_CLUSTER_NAME = Kubernetes cluster name, e.g. "Govstack-sandbox-cluster-dev"
* AWS_DEFAULT_REGION = eu-central-1
* CHART_NAMESPACE = open-imis

More information in [Confluence](https://govstack-global.atlassian.net/wiki/spaces/DEMO/pages/119046145/AWS+Accounts). 

## Circle CI Deploy Workflow:
To start a workflow, follow these steps:

1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Add parameter of type "Boolean", named "deploy_allowed" and set value to "true"
5. Then trigger the pipeline