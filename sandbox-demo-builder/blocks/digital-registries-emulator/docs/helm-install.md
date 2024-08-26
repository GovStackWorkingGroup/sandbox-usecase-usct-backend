# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade digital-registries-bb-emulator ./helm --install --create-namespace --namespace digital-registries-bb-emulator --dry-run --debug
```

## Install chart

```
    helm upgrade digital-registries-bb-emulator ./helm --install --create-namespace --namespace digital-registries-bb-emulator
```

## Install chart with persisted database state

 Note: The DB state will not be flushed on every pod restart
```
    helm upgrade --set digital_registries_bb_emulator.dbPersist.flushStorageOnInit=false digital-registries-bb-emulator ./helm --install --create-namespace --namespace digital-registries-bb-emulator
```

## Install chart with no persisted database state

 Useful for development purposes
 Note: The DB state will be flushed on every pod restart
```
    helm upgrade --set digital_registries_bb_emulator.dbPersist.flushStorageOnInit=true digital-registries-bb-emulator ./helm --install --create-namespace --namespace digital-registries-bb-emulator
```

## Uninstall chart

Uninstall chart
```
    helm uninstall digital-registries-bb-emulator --namespace digital-registries-bb-emulator
```