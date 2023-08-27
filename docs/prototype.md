# Umbrella chart

## Pull an Image from a Private Registry
https://kubernetes.io/docs/concepts/containers/images/#using-a-private-registry


If you are developing with your cluster running on local machine, you don't even need to do that. You can have the pod reuse the image that you have downloaded to your local cache by either setting the `imagePullPolicy` under container spec to `IfNotPresent`, or using a specific tag instead of `latest` for your image.



## issue

│ stream logs failed container "mock-sris-backend" in pod "mock-sris-85d65c8bc8-z5k25" is waiting to start: trying and failing to pull image for mock-sris/mock-sris-85d65c8bc8-z5k25 (mock-sris-backend)                              │






1. eval $(minikube docker-env) eval $(minikube -p minikube docker-env)
2. minikube start
3. echo $MINIKUBE_ACTIVE_DOCKERD

minikube image load mock-sris:0.0.2
minikube -p minikube docker-env ??????

https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env


from https://serverfault.com/questions/964307/kubernetes-deployment-failed-to-pull-image-with-local-registry-minikube

https://kubernetes.io/docs/concepts/containers/images/#using-a-private-registry










463471358064.dkr.ecr.eu-central-1.amazonaws.com/bb/payments/emulator/dev-backend:0.0.1
463471358064.dkr.ecr.eu-central-1.amazonaws.com/bb/payments/emulator/dev-backend



## 
Add subsystem `PAYMENT`

add services 

REST service

http://payment-bb-emulator.mock-sris.svc.cluster.local:8080/v3/api-docs

api


kubectl logs sandbox-xroad-ss2-6787c94fcd-sqb27 -n mock-sris