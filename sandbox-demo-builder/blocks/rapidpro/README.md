# RapidPro / Message Building Block
RapidPro is a platform for visually building interactive messaging applications. To learn more, please visit the project site at http://rapidpro.github.io/rapidpro.

## Deployment

https://github.com/rapidpro/rapidpro-docker

https://riseuplabs.com/rapidpro-installation-guide/

https://github.com/nyaruka/rapidpro



### Useful commands

```shell
helm install rapidpro ./rapidpro/ --create-namespace --namespace rapidpro
```

```shell
helm upgrade --install rapidpro ./rapidpro/ --create-namespace --namespace rapidpro
```

```shell
helm install --debug --dry-run rapidpro ./rapidpro/ --create-namespace --namespace rapidpro
```

```shell
helm uninstall rapidpro --namespace rapidpro
```

### Installing separetly the MiniO helm Chart (toy-setup)

Minimal toy setup for testing purposes can be deployed using:

```bash
helm repo add minio https://charts.min.io/
```

```bash
helm install --set resources.requests.memory=512Mi --set replicas=1 --set persistence.enabled=false --set mode=standalone --set rootUser=rootuser,rootPassword=rootpass123 --generate-name minio/minio --namespace rapidpro
```


## Alternatives repositories

https://github.com/onaio/helm-chart-rapidpro