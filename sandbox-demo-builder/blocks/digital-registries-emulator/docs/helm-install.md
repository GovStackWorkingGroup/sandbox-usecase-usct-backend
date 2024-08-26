# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade digital-registries-emulator ./digital-registries-emulator --install --create-namespace --namespace digital-registries-emulator --dry-run --debug > temp.yaml
```

## Install chart

```
    `helm upgrade digital-registries-emulator ./digital-registries-emulator --install --create-namespace --namespace digital-registries-emulator`
```

## Install chart with persisted database state

 Note: The DB state will not be flushed on every pod restart
```
    helm upgrade --set digital_registries_bb_emulator.dbPersist.flushStorageOnInit=false digital-registries-emulator ./digital-registries-emulator --install --create-namespace --namespace digital-registries-emulator
```

## Install chart with no persisted database state

 Useful for development purposes
 Note: The DB state will be flushed on every pod restart
```
    helm upgrade --set digital_registries_emulator.dbPersist.flushStorageOnInit=true digital-registries-emulator ./digital-registries-emulator --install --create-namespace --namespace digital-registries-emulator
```

## Uninstall chart

Uninstall chart
```
    helm uninstall digital-registries-emulator --namespace digital-registries-emulator
```