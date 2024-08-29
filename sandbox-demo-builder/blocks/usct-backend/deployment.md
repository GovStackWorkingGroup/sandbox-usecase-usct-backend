# Deployment


```shell
helm install usct-backend ./usct-backend/ --create-namespace --namespace usct
```

```shell
helm upgrade --install usct-backend ./usct-backend/ --create-namespace --namespace usct
```

```shell
helm install --debug --dry-run usct-backend ./usct-backend/ --create-namespace --namespace usct
```

```shell
helm uninstall usct-backend --namespace usct
```
