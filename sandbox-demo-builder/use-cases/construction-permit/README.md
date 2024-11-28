# Construction permit
The Construction permit Use Case is created with the idea of proposing a way for individuals and businesses to acquire
permits for construction, renovation or demolition projects from the national government easily
via a streamlined process.

[Live Demo](https://bp.playground.sandbox-playground.com/housing/construction-permit)

## Implementation Frontend
https://github.com/GovStackWorkingGroup/sandbox-usecase-bp-frontend

## Implementation Backend
https://github.com/GovStackWorkingGroup/sandbox-app-rpc-backend


## Useful commands

### Frontend
Helm Installation

* Debug Helm Install Upgrade command for Govstack sandbox
```
helm upgrade bp-frontend ./frontend --install --create-namespace --namespace bp --dry-run --debug
``````

* Install chart
```
helm upgrade bp-frontend ./frontend --install --create-namespace --namespace bp
``` 

* Uninstall chart
```
helm uninstall bp-frontend --namespace bp
```

### Backend

# Helm Installation

* Debug chart

```
    helm upgrade rpc-backend ./backend --install --create-namespace --namespace rpc-backend --dry-run --debug
```

* Install chart

```
    helm upgrade rpc-backend ./backend --install --create-namespace --namespace rpc-backend
```

* Install chart with persisted database state

Note: The DB state will not be flushed on every pod restart
```
    helm upgrade --set rpc_backend.dbPersist.flushStorageOnInit=false rpc-backend ./backend --install --create-namespace --namespace rpc-backend
```

* Install chart with no persisted database state

Useful for development purposes
Note: The DB state will be flushed on every pod restart
```
    helm upgrade --set rpc_backend.dbPersist.flushStorageOnInit=true rpc-backend ./backend --install --create-namespace --namespace rpc-backend
```

* Uninstall chart

Uninstall chart
```
    helm uninstall rpc-backend --namespace rpc-backend
```