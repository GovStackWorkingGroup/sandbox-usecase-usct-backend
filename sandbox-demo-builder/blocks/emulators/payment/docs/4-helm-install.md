# Helm Installation

## Debug chart

Helm Upgrade command for Govstack sandbox
```
    helm upgrade payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator --dry-run --debug
```

## Install chart

```
    helm upgrade payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator
```

## Install chart with persisted database state

 Note: The DB state will not be flushed on every pod restart
```
    helm upgrade --set payment_bb_emulator.dbPersist.flushStorageOnInit=false payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator
```

## Install chart with no persisted database state

 Useful for development purposes
 Note: The DB state will be flushed on every pod restart
```
    helm upgrade --set payment_bb_emulator.dbPersist.flushStorageOnInit=true payment-bb-emulator ./helm --install --create-namespace --namespace payment-bb-emulator
```

## Uninstall chart

Uninstall chart
```
    helm uninstall payment-bb-emulator --namespace payment-bb-emulator
```