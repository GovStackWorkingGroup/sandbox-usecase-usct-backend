# Early Warning System

## Retention policy

This Early Warning System is designed for demonstration purposes only and is not intended for production use.
The demo is publicly available, allowing anyone to try its functionality.
We do not need to retain tested messages. They should be removed periodically.

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