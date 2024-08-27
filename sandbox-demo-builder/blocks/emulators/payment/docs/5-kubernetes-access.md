# Kubernetes Access:

## Port forward to service or specific pod
- pod: payment-bb-emulator-0
- service: payment-bb-emulator-service

## Access swagger:
```
http://localhost:{port-from-port-forward}/swagger-ui/index.html
```

## Swagger Api doc JSON:
```
http://localhost:{port-from-port-forward}/v3/api-docs
```

## Swagger Api doc YAML:
```
http://localhost:{port-from-port-forward}/v3/api-docs.yaml
```

## H2 Console for DB ui:
```
http://localhost:{port-from-port-forward}/h2-console
```


## Health Check URL-s:
```
http://localhost:{port-from-port-forward}/actuator/health

http://localhost:{port-from-port-forward}/actuator/health/liveness

http://localhost:{port-from-port-forward}/actuator/health/readiness
```