# Early Warning System

### Useful commands

```shell
helm install early-warning ./ --create-namespace --namespace early-warning
```

```shell
helm upgrade --install early-warning ./ --create-namespace --namespace early-warning
```

```shell
helm install --debug --dry-run early-warning ./ --create-namespace --namespace early-warning > temp.yaml
```

```shell
helm uninstall early-warning --namespace early-warning
```