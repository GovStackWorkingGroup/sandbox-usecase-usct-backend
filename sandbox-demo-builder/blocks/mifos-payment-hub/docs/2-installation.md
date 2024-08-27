# Installation

## Install chart

Helm dependency update
```
    helm dependency update helm/g2p-sandbox
```

Helm Upgrade command for Govstack sandbox
```
    helm upgrade -f helm/g2p-sandbox/values.yaml -f helm/g2p-sandbox/values-sandbox.yaml --set fin-engine.namespace=paymenthub g2pconnect helm/g2p-sandbox --install --create-namespace --namespace paymenthub
```

NOTE: If different namespace is used should be changed in " helm/g2p-sandbox/values-sandbox.yaml" for hosts related with "ph_ee_connector_ams_mifos"

Example: 
    https://fineract-server-local.paymenthub.svc.cluster.local/ ->
    https://fineract-server-local.namespace.svc.cluster.local/

## Uninstall chart

Uninstall chart
```
    helm uninstall g2pconnect --namespace paymenthub
```

Delete all related Persistent Volume Claims
```
    kubectl delete pvc data-fineract-mysql-0 data-operationsmysql-0 data-zeebe-zeebe-0 ph-ee-elasticsearch-ph-ee-elasticsearch-0 -n paymenthub
```

Delete Elasticsearch secrets
```
kubectl delete secrets elastic-certificates elastic-certificate-pem elastic-certificate-crt -n paymenthub
```


## Known Issue Payment hub EE:
Migration script race condition Operation app startup issue work around

### Solution

1. port forward ops-mysql(pod: "operationsmysql") -3307
2. connect the mysql with root password 
3. delete tenants 
4. Run the SQL scripts which didn’t run successfully

```
    DROP DATABASE `tenants`;
    DROP DATABASE `rhino`;
    DROP DATABASE `gorilla`;

    CREATE DATABASE `tenants`;
    GRANT ALL PRIVILEGES ON `tenants`.* TO 'mifos';
    CREATE DATABASE `rhino`;
    CREATE DATABASE `gorilla`;
    GRANT ALL PRIVILEGES ON `rhino`.* TO 'mifos';
    GRANT ALL PRIVILEGES ON `gorilla`.* TO 'mifos';
    GRANT ALL ON *.* TO 'root'@'%';
```
5. restart ops-app (pod: "ph-ee-operations-app")

More information about this issue: [INFO](https://github.com/openMF/ph-ee-env-labs/tree/master/helm/g2p-sandbox#readme)

## Known Issue Fineract

There is race condition with migrations runned in fineract-server pod. When migrations are not runned completely login in fineract "Community app" is not possible with tennant "gorilla".

### Solution

Restart fineract-server pod, then check that database "gorilla" in fineract-mysql pod is populated.

## ElasticSearch secrets

Source documentation provied from Mifos: [INFO](https://docs.google.com/document/d/1Pk4fHdAONAwZ9j65YuI8qA8MgDmv_oMnlvqNUQGsMTA/edit)

```
    // Curent elasticsearch version 7.16.3
    // Current chart namespace "paymenthub"
    // Change "elasticsearch version" or/and "namespace" if other namespace is used 
    //.  when chart is installed or different version
    //.  of elasticsearch is used in the chart 

    docker pull docker.elastic.co/elasticsearch/elasticsearch:7.16.3

    docker run --name elastic-helm-charts-certs -i -w /app \
            docker.elastic.co/elasticsearch/elasticsearch:7.16.3 \
            /bin/sh -c " \
                elasticsearch-certutil ca --out /app/elastic-stack-ca.p12 --pass '' && \
                elasticsearch-certutil cert --name security-master --dns security-master --ca /app/elastic-stack-ca.p12 --pass '' --ca-pass '' --out /app/elastic-certificates.p12"

    docker cp elastic-helm-charts-certs:/app/elastic-certificates.p12 ./ 
    openssl pkcs12 -nodes -passin pass:'' -in elastic-certificates.p12 -out elastic-certificate.pem
    openssl x509 -outform der -in elastic-certificate.pem -out elastic-certificate.crt

    kubectl create secret generic elastic-certificates --from-file=elastic-certificates.p12 --namespace paymenthub
    kubectl create secret generic elastic-certificate-pem --from-file=elastic-certificate.pem --namespace paymenthub
    kubectl create secret generic elastic-certificate-crt --from-file=elastic-certificate.crt --namespace paymenthub
```