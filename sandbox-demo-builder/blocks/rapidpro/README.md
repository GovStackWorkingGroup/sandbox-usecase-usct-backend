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
