# Early Warning System

### Useful commands

```shell
helm install early-warnings ./ --create-namespace --namespace early-warnings
```

```shell
helm upgrade --install early-warnings ./ --create-namespace --namespace early-warnings
```

```shell
helm install --debug --dry-run early-warnings ./ --create-namespace --namespace early-warnings > temp.yaml
```

```shell
helm uninstall early-warnings --namespace early-warnings
```