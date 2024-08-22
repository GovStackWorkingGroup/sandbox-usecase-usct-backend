# RapidPro / Message Building Block
RapidPro is a platform for visually building interactive messaging applications. To learn more, please visit the project site at http://rapidpro.github.io/rapidpro.

## Deployment

https://github.com/rapidpro/rapidpro-docker

https://riseuplabs.com/rapidpro-installation-guide/

https://github.com/nyaruka/rapidpro



### Useful commands

```shell
helm install temprapid ./rapidpro/ --create-namespace --namespace temprapid
```

```shell
helm upgrade --install temprapid ./rapidpro/ --create-namespace --namespace temprapid
```

```shell
helm install --debug --dry-run temprapid ./rapidpro/ --create-namespace --namespace temprapid
```

```shell
helm uninstall temprapid --namespace temprapid
```

```shell
  kubectl create deployment test --image=busybox -n temprapid -- sleep 3600
```

