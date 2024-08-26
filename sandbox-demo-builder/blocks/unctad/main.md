# UNCTAD

1. Creation of pre-configured image and push/deployment to some repository

   The pre-configured images were created manually:

    - Run Redis database in a Docker container:
      ``` bash
       $ docker run --name redis-db -d redis
      ```

    - Run PostgreSQL database in a Docker container:
      ``` bash
      $ docker run -d -v postgres-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=docker --name postgres-db -p 5432:5432 postgres:12
      ```

    - Perform data migration in the PostgreSQL docker container:
      ``` bash
       $ docker exec -it --user postgres postgres-db bin/bash -c "psql -d postgres -q -c \"CREATE ROLE gdb WITH PASSWORD 'xxxxxxxx';\""
       $ docker exec -it --user postgres postgres-db bin/bash -c "psql -d postgres -q -c \"CREATE DATABASE gdb WITH OWNER gdb;\""
       $ docker exec -it --user postgres postgres-db bin/bash -c "psql -d postgres -q -c \"ALTER ROLE gdb WITH LOGIN;\"" 
      ```

    - Perform Docker login:
      ``` bash
       $ docker login --username=$DOCKER_USERNAME --password=$DOCKER_PASSWORD
      ```

    - Set some environment variables:

        * ENV=LIVE
        * POSTGRES_HOST - host for PostgreSQL database, e.g. 172.17.0.3
        * SERVICE_HOST - host for Redis database, e.g. 172.17.0.2
        * GDB_POSTGRES_DB_NAME
        * GDB_POSTGRES_DB_USER
        * GDB_POSTGRES_DB_PASSWORD
        * GDB_OAUTH_CLIENT_ID - OAuth client ID, e.g. "gdb-client"
        * GDB_OAUTH_CLIENT_SECRET - OAuth client password, e.g. "changeme"
        * TRANSLATION_SERVICE_URL - URL for translation service, e.g. "https://translations.test.eregistrations.org"
        * DEFAULT_LANGUAGE - default language, e.g. "en"

    - Run GDB in a Docker container:
      ``` bash
       $ docker run -d -e ENV=LIVE -e DATABASE_HOST=$POSTGRES_HOST -e DATABASE_NAME=$GDB_POSTGRES_DB_NAME -e\
         DATABASE_USERNAME=$GDB_POSTGRES_DB_USER -e DATABASE_PASSWORD=$GDB_POSTGRES_DB_PASSWORD -e REDIS_HOST=$SERVICE_HOST -e\
         ALLOWED_HOSTS=gdb -e CORS_ORIGIN_WHITELIST="" -e AUTH_SERVICE_TYPE_1=KEYCLOAK -e AUTH_SERVICE_REALM_1=gdb -e \
         AUTH_SERVICE_BACKEND_URL_1=http://keycloak:8080 -e AUTH_SERVICE_PUBLIC_URL_1=https://login.$YOUR_DOMAIN_NAME/ -e \
         AUTH_SERVICE_ID_1=0 -e AUTH_SERVICE_NAME_1=gdb -e AUTH_SERVICE_CLIENT_ID_1=$GDB_OAUTH_CLIENT_ID -e \
         AUTH_SERVICE_CLIENT_SECRET_1=$GDB_OAUTH_CLIENT_SECRET -e AUTH_SERVICE_CLIENT_SCOPE_1="openid email profile" -e \
         TRANSLATION_SERVICE_URL=$TRANSLATION_SERVICE_URL -e AUTH_SERVICE_CLIENT_CALLBACK="login/callback" -e \
         LANGUAGES=$DEFAULT_LANGUAGE -e DEMAND_AUTHORIZATION_HEADER=true -e UPLOAD_FILE_SIZE_LIMIT=26214400 -e \
         APP_TITLE=gdb --name gdb -p 8080:3003 unctad/license-registry:RC
      ```   

    - Commit the container to an image:
       ``` bash 
        $ docker commit <CID> <IMAGE_NAME> 
       ```

    - Connect Docker to a container repository in the cloud e.g. AWS ECR:
      ``` bash 
       $ aws ecr get-login-password --region <AWS_REGION> --profile <AWS_PROFILE> | docker login --username AWS --password-stdin <AWS_ECR_URL> 
      ```

    - Tag the Docker image:
       ``` bash 
        $ docker tag <IMAGE_NAME>:latest <AWS_ECR_REPO>:<IMAGE_NAME> 
       ```

    - Push the pre-configured Docker image to container repository e.g. AWS ECR:
       ``` bash 
        $ docker push <AWS_ECR_REPO>:<IMAGE_NAME> 
       ```

        * CID - docker container id (can be obtained by for example by ``` $ export CID=$(docker ps -aqf "name=gdb") ``` where the  ```name``` is the container name)
        * IMAGE_NAME - the name of the docker image, e.g. gdb
        * AWS_REGION - the region in the cloud, where the repository is located e.g. eu-west-1
        * AWS_PROFILE - the AWS identification profile used for connection e.g. govstack-sandbox
        * AWS_ECR_URL - the URL for the AWS ECR e.g. 123456789.dkr.ecr.eu-west-1.amazonaws.com
        * AWS_ECR_REPO - the repository URL in AWS ECR, e.g 123456789.dkr.ecr.eu-west-1.amazonaws.com/digital-registries/gdb

2. Install secrets (passwords) to the Kubernetes Cluster with Helm charts

   First, some environment variables need to be set:
    * NAMESPACE - the name of the namespace to which the GDB will be intalled in the Kubernetes Cluster
    * SECRETS_ENABLED - boolean value indicating if secrets (passwords) should be generated in the Kubernetes Cluster or not (recommended value is ```true``` in this step)
    * GDB_ENABLED - boolean value indicating whether GDB should be installed or not
    * DB_ENABLED - boolean value indicating if PostgreSQL and Redis databases should be installed to the Kubernetes Cluster or not (recommended value is ```false``` in this step)
    * APPLICATION_ENABLED - boolean value indicating if the GDB should be installed to the Kubernetes Cluster or not (recommended value is ```false``` in this step)

   Next, the ```values.yaml``` file is copied to a temporary values file:
   ``` bash 
    $ cp ./digital-registries/values.yaml val.yaml 
   ```

   Now, the placeholders for those environment variables in the temporary values file will be substituted with real values taken from those environment variables set previously:
   ``` bash 
    $ sed -i 's/${NAMESPACE}/'"$NAMESPACE"'/g' val.yaml
    $ sed -i 's/${GDB_ENABLED}/'"$GDB_ENABLED"'/g' val.yaml 
    $ sed -i 's/${DB_ENABLED}/'"$DB_ENABLED"'/g' val.yaml
    $ sed -i 's/${SECRETS_ENABLED}/'"$SECRETS_ENABLED"'/g' val.yaml
    $ sed -i 's/${APPLICATION_ENABLED}/'"$APPLICATION_ENABLED"'/g' val.yaml
   ```

   Finally, that temporary values file is used to install secrets (passwords) to Kubernetes Cluster:
   ``` bash
    $ helm upgrade --install digital-registries ./digital-registries/ -f val.yaml 
   ```
3. Install PostgreSQL and Redis databases to the Kubernetes Cluster with Helm charts


            export NAMESPACE="${AWS_NAMESPACE}"
            export GDB_ENABLED="${GDB_ENABLED}"
            export PGDATA_FOLDER="${PGDATA}"
            export PGDATA=$(echo ${PGDATA_FOLDER//\//\\/})
            export DB_ENABLED="true"
            export SECRETS_ENABLED="false"
            export APPLICATION_ENABLED="false"

First, some environment variables need to be set:
* NAMESPACE - the name of the namespace to which the GDB will be intalled in the Kubernetes Cluster
* SECRETS_ENABLED - boolean value indicating if secrets (passwords) should be generated in the Kubernetes Cluster or not (recommended value is ```true``` in this step)
* GDB_ENABLED - boolean value indicating whether GDB should be installed or not
* DB_ENABLED - boolean value indicating if PostgreSQL and Redis databases should be installed to the Kubernetes Cluster or not (recommended value is ```true``` in this step)
* APPLICATION_ENABLED - boolean value indicating if the GDB should be installed to the Kubernetes Cluster or not (recommended value is ```false``` in this step)
* PGDATA - folder to keep PostgreSQL database data in, e.g. "/var/lib/postgresql/data"

Next, the ```values.yaml``` file is copied to a temporary values file:
   ``` bash
    $ cp ./digital-registries/values.yaml val.yaml 
   ```

Now, the placeholders for those environment variables in the temporary values file will be substituted with real values taken from those environment variables set previously:
   ``` bash
    $ sed -i 's/${NAMESPACE}/'"$NAMESPACE"'/g' val.yaml
    $ sed -i 's/${GDB}/'"$GDB_ENABLED"'/g' val.yaml
    $ sed -i 's/${PGDATA}/'"$PGDATA"'/g' val.yaml
    $ sed -i 's/${DB_ENABLED}/'"$DB_ENABLED"'/g' val.yaml
    $ sed -i 's/${SECRETS_ENABLED}/'"$SECRETS_ENABLED"'/g' val.yaml
    $ sed -i 's/${APPLICATION_ENABLED}/'"$APPLICATION_ENABLED"'/g' val.yaml 
   ```

Finally, that temporary values file is used to install PostgreSQL database to Kubernetes Cluster:
   ``` bash
    $ helm upgrade --install digital-registries ./digital-registries/ -f val.yaml 
   ```

4. Configure the PostgreSQL database in the Kubernetes Cluster with Helm charts

   First, some environment variables need to be set:
    * NAMESPACE - the name of the namespace to which the GDB will be installed in the Kubernetes Cluster
    * POSTGRES_USER - admin user's name for PostgreSQL database, e.g. "postgres"
    * ADMIN_PASS - admin password for PostgreSQL database, e.g.
   ``` bash
    $ $(kubectl get secret govstack-gdb-postgres-admin-secret -o jsonpath='{.data.password}' --namespace $NAMESPACE | base64 --decode)
   ```

   Next, configure PostgreSQL database password:
   ``` bash
    $ kubectl exec -it service/govstack-gdb-postgres -n $NAMESPACE -- psql -h localhost -U $POSTGRES_USER -c "ALTER USER ${POSTGRES_USER} WITH PASSWORD '${ADMIN_PASS}'"
   ```
5. Install Digital Registries BB

   First, some environment variables need to be set:
    * NAMESPACE - the name of the namespace to which the GDB will be intalled in the Kubernetes Cluster
    * SECRETS_ENABLED - boolean value indicating if secrets (passwords) should be generated in the Kubernetes Cluster or not (recommended value is ```true``` in this step)
    * GDB_ENABLED - boolean value indicating whether GDB should be installed or not
    * DB_ENABLED - boolean value indicating if PostgreSQL and Redis databases should be installed to the Kubernetes Cluster or not (recommended value is ```true``` in this step)
    * APPLICATION_ENABLED - boolean value indicating if the GDB should be installed to the Kubernetes Cluster or not (recommended value is ```false``` in this step)
    * PGDATA - folder to keep PostgreSQL database data in, e.g. "/var/lib/postgresql/data"
    * ENV - environment for GDB, e.g. "live"
    * ECR_GDB_REPO_NAME - GDB container registry repository name, e.g. 123456789.dkr.ecr.eu-west-1.amazonaws.com\/digital-registries\/gdb (note: slashes have to be escaped)
    * DATABASE_HOST - host for PostgreSQL database in the Kubernetes Cluster, e.g.
   ``` bash
    $(kubectl get service govstack-gdb-postgres -o jsonpath='{.spec.clusterIP}' -n $NAMESPACE)
   ```
    * DATABASE_NAME - name of the database in PostgreSQL, e.g. "gdb"
    * DATABASE_USERNAME - username in the PostgreSQL database in the Kuberetes Cluster, e.g. "gdb"
    * DATABASE_PASSWORD - password for the PostgreSQL database in the Kubernetes Cluster, e.g.
   ``` bash
    $(kubectl get secret govstack-gdb-postgres-admin-secret -o jsonpath='{.data.password}' --namespace $NAMESPACE | base64 --decode)
    ```
    * REDIS_HOST - host for Redis database in the Kubernetes Cluster, e.g.
   ``` bash
    $(kubectl get service govstack-gdb-redis -o jsonpath='{.spec.clusterIP}' --namespace $NAMESPACE)
   ``` 
    * ALLOWED_HOSTS - list of allowed hosts, e.g. "gdb,172.19.0.2"
    * CORS_ORIGIN_WHITELIST - CORS whitelist, e.g. "localhost"
    * AUTH_SERVICE_TYPE_1 - authentication service type, e.g. "KEYCLOAK"
    * AUTH_SERVICE_REALM_1 - authentication service realm, e.g. "gdb"
    * AUTH_SERVICE_BACKEND_URL_1 - authentication service backend URL, e.g. "http:\/\/keycloak:8080" (note: slashes have to be escaped)
    * AUTH_SERVICE_PUBLIC_URL_1 - authentication service public URL, e.g. "https:\/\/login.gdb" (note: slashes have to be escaped)
    * AUTH_SERVICE_ID_1 - authentication service identificator, e.g. "0"
    * AUTH_SERVICE_NAME_1 - authentication service name, e.g. "gdb-auth"
    * AUTH_SERVICE_CLIENT_ID_1 - authentication service client id, e.g. "gdb-client"
    * AUTH_SERVICE_CLIENT_SECRET_1 - authentication service client password, e.g. "changeme"
    * AUTH_SERVICE_CLIENT_SCOPE_1 - authentication service client scope, e.g. "openid email profile"
    * TRANSLATION_SERVICE_URL - translation service URL, e.g. "https:\/\/translations.test.eregistrations.org" (note: slashes have to be escaped)
    * AUTH_SERVICE_CLIENT_CALLBACK - authentication service client callback, e.g. "login\/callback" (note: slashes have to be escaped)
    * LANGUAGES - list of languages, e.g. "en"
    * DEMAND_AUTHORIZATION_HEADER - demand authorization header, e.g. "true"
    * UPLOAD_FILE_SIZE_LIMIT - upload file size limit in bytes, e.g. "26214400"
    * APP_TITLE - application title, e.g. "gdb"

   Now, the placeholders for those environment variables in the temporary values file will be substituted with real values taken from those environment variables set previously:
   ``` bash
    $ sed -i 's/${NAMESPACE}/'"$NAMESPACE"'/g' val.yaml
    $ sed -i 's/${ENV}/'"$ENV"'/g' val.yaml
    $ sed -i 's/${PGDATA}/'"$PGDATA"'/g' val.yaml
    $ sed -i 's/${DATABASE_ENABLED}/'"$DB_ENABLED"'/g' val.yaml
    $ sed -i 's/${SECRETS_ENABLED}/'"$SECRETS_ENABLED"'/g' val.yaml
    $ sed -i 's/${APPLICATION_ENABLED}/'"$APPLICATION_ENABLED"'/g' val.yaml
    $ sed -i 's/${GDB_ENABLED}/'"$GDB_ENABLED"'/g' val.yaml
    $ sed -i 's/${ECR_GDB_REPO}/'"$ECR_GDB_REPO_NAME"'/g' val.yaml
    $ sed -i 's/${DATABASE_HOST}/'"$DATABASE_HOST"'/g' val.yaml
    $ sed -i 's/${DATABASE_NAME}/'"$DATABASE_NAME"'/g' val.yaml
    $ sed -i 's/${DATABASE_USERNAME}/'"$DATABASE_USERNAME"'/g' val.yaml
    $ sed -i 's/${DATABASE_PASSWORD}/'"$DATABASE_PASSWORD"'/g' val.yaml
    $ sed -i 's/${REDIS_HOST}/'"$REDIS_HOST"'/g' val.yaml
    $ sed -i 's/${ALLOWED_HOSTS}/'"$ALLOWED_HOSTS"'/g' val.yaml
    $ sed -i 's/${CORS_ORIGIN_WHITELIST}/'"$CORS_ORIGIN_WHITELIST"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_TYPE_1}/'"$AUTH_SERVICE_TYPE_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_REALM_1}/'"$AUTH_SERVICE_REALM_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_BACKEND_URL_1}/'"$AUTH_SERVICE_BACKEND_URL_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_PUBLIC_URL_1}/'"$AUTH_SERVICE_PUBLIC_URL_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_ID_1}/'"$AUTH_SERVICE_ID_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_NAME_1}/'"$AUTH_SERVICE_NAME_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_CLIENT_ID_1}/'"$AUTH_SERVICE_CLIENT_ID_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_CLIENT_SECRET_1}/'"$AUTH_SERVICE_CLIENT_SECRET_1"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_CLIENT_SCOPE_1}/'"$AUTH_SERVICE_CLIENT_SCOPE_1"'/g' val.yaml
    $ sed -i 's/${TRANSLATION_SERVICE_URL}/'"$TRANSLATION_SERVICE_URL"'/g' val.yaml
    $ sed -i 's/${AUTH_SERVICE_CLIENT_CALLBACK}/'"$AUTH_SERVICE_CLIENT_CALLBACK"'/g' val.yaml
    $ sed -i 's/${LANGUAGES}/'"$LANGUAGES"'/g' val.yaml
    $ sed -i 's/${DEMAND_AUTHORIZATION_HEADER}/'"$DEMAND_AUTHORIZATION_HEADER"'/g' val.yaml
    $ sed -i 's/${UPLOAD_FILE_SIZE_LIMIT}/'"$UPLOAD_FILE_SIZE_LIMIT"'/g' val.yaml
    $ sed -i 's/${APP_TITLE}/'"$APP_TITLE"'/g' val.yaml          
   ```

   Next, the ```values.yaml``` file is copied to a temporary values file:
   ``` bash
    $ cp ./digital-registries/values.yaml val.yaml 
   ```

   Finally, that temporary values file is used to install Digital Registries(GDB) to Kubernetes Cluster:
   ``` bash
    $ helm upgrade --install digital-registries ./digital-registries/ -f val.yaml 
   ```

6. Structure of Helm charts

   The "digital-registries" chart contains a sub-chart:

    * A chart for [GDB](digital-registries/unctad/charts/gdb)
      the sub-chart contains also the following templates:
        - [application.yaml](digital-registries/unctad/charts/gdb/templates/application.yaml) for installing GDB pod and service to Kubernetes Cluster
        - [db.yaml](digital-registries/unctad/charts/gdb/templates/db.yaml) for installing GDB PostgresSQL and Redis databases to Kubernetes Cluster
        - [secret.yaml](digital-registries/unctad/charts/gdb/templates/secret.yaml) for installing GDB secrets to Kubernetes Cluster

7. CI / CD pipeline

   There is also a [CircleCI pipeline](.circleci/config.yml) created for automation of those previous steps.

   The pipeline uses the following environment variables (which can be set under configuration of the CircleCI project):

    * AWS_ACCOUNT_ID	- identity account name for accessing the cluster and its resources e.g. "123456789"
    * AWS_CLUSTER_NAME - Kubernetes cluster name, e.g. "Govstack-sandbox"
    * AWS_DEFAULT_REGION - default region of the cluster, e.g. "eu-west-1"
    * AWS_ECR_GDB_REPO_NAME - name for the container registry of the GDB, e.g. "gdb"
    * AWS_ECR_REPO_NAME_PREFIX - repository prefix for digital-registries in the container registry, e.g. "digital-registries"
    * AWS_NAMESPACE - namespace name, where the cluster resides in the cloud, e.g. "govstack"
    * GDB_ENABLED - boolean value indicating whether GDB should be installed or not
    * GENERATE_NEW_SECRETS - boolean value indicating whether secrets (passwords) should be generated for the GDB in the cluster
    * PGDATA - folder to keep PostgreSQL database data in, e.g. "/var/lib/postgresql/data"
    * POSTGRES_USER - admin user's name for PostgreSQL database, e.g. "postgres"