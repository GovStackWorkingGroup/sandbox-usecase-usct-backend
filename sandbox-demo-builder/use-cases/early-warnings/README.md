# Early Warnings System
Provide proof for a backend architecture enabling the Early Warnings App to scale in terms of geographically and\
organizationally distributed users (information providers and consumers) and in terms of usability\
by end users (especially back channel to provide feedback).

More information about the Early Warning System can be found in the here:\ 
https://govstack-global.atlassian.net/wiki/spaces/DEMO/pages/737673221/Husika+Early+Warning+-+Objective+scope+roadmap

## Run locally
Helm charts can be run locally using Minikube. To run locally, it is suggested to build a new Frontend Docker image
with the correct addresses of the backend (localhost).

## Retention policy
This Early Warning System is designed for demonstration purposes only and is not intended for production use.
The demo is publicly available, allowing anyone to try its functionality.
We do not need to retain test information; it's removed every day.
At the same time it could be configured to retain data for a longer period of time.

### Useful commands

First time for Kafka charts

```shell
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm dep build
```

```shell
helm install early-warnings ./early-warnings --create-namespace --namespace early-warnings
```

```shell
helm upgrade --install early-warnings ./early-warnings --create-namespace --namespace early-warnings
```

```shell
helm install --debug --dry-run early-warnings ./early-warnings --create-namespace --namespace early-warnings > temp.yaml
```

```shell
helm uninstall early-warnings --namespace early-warnings
```