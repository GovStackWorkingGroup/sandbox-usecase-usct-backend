# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade digital-registries-bb-emulator ./digital-registries-bb-emulator --install --create-namespace --namespace digital-registries-bb-emulator --dry-run --debug
```

## Install chart

```
    `helm upgrade digital-registries-bb-emulator ./digital-registries-bb-emulator --install --create-namespace --namespace digital-registries-bb-emulator`
```

## Uninstall chart

Uninstall chart
```
    helm uninstall digital-registries-bb-emulator --namespace digital-registries-bb-emulator
```