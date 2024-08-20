# Baserow / Registry Building Block
Baserow is an open platform to create scalable databases without coding.
To learn more, please visit https://baserow.io/

There are multiple ways to deploy Baserow in self-hosting mode, here's a quick guide for running it with Helm and Docker.

## Deployment with Helm chart

To run Baserow with the minimum configuration that defines the domains you would like it to run on, edit the provided **config.yaml** file.

For more instructions, follow the instructions from below.
https://baserow.io/docs/installation%2Finstall-with-helm#deploying-baserow-using-helm-and-k3s

### Useful commands

```shell
helm repo add baserow-chart https://baserow.gitlab.io/baserow-chart
```

```shell
helm install my-baserow baserow-chart/baserow --namespace baserow --create-namespace --values config.yaml
```

```shell
helm upgrade my-baserow baserow-chart/baserow --namespace baserow --values config.yaml
```

```shell
helm install --debug --dry-run my-baserow baserow-chart/baserow --namespace baserow --create-namespace --values config.yaml
```

```shell
helm uninstall my-baserow --namespace baserow
```

## Deployment with Docker

https://baserow.io/docs/installation%2Finstall-with-docker
