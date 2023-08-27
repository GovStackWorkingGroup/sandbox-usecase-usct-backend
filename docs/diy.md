# How to deploy USCT use case to minikube 

## Prerequisites 

* [minikube](https://minikube.sigs.k8s.io/docs/)
* [Docker](https://www.docker.com/)
* [Helm charts](https://helm.sh/docs/topics/charts/)

## Installation 
1. Pull the [helm chart](./../use-case-helm).
2. Build a docker images from the [sources](main.md#building-block-diagram) or [contact GovStack](https://www.govstack.global/) to get access to the container registry.
3. Use [useful commands](./main.md#useful-commands) to up and run the use case.


4. Port forward UI of the Security Server 3 (aka Provider Security Server)

``` shell
kubectl port-forward \
    -n mock-sris \
    service/ss3 4000 4000
```

5. Navigate to 'Clients' tab and press 'Add subsystem' button.
6. Field 'Payment' name as Subsystem Code.
7. Press Yes in Register client popup window.
8. Go into new 'PAYMENT' subsystem 
9. Click on 'Services' tub. 
10. Press 'Add REST' button.
11. Choose 'OpenAPI 3 Description' option 
12. Fiel 'http://payment-bb-emulator.mock-sris.svc.cluster.local:8080/v3/api-docs' into URL placeholder and 'api' into Service Code. 
13. Enable a new created service --> click on the related switch.
13. Expand a new created REST definition 
14. Press 'Add subjects' in the 'Service Parameters' tab
15. Press 'Search' button 
16. Check 'Client' and 'Provider' checkboxes and press 'Add selected'
17. Close popup


Current set include X-road set:

1. Central server
2. Security server management (for connecting to the central server)
3. Security server for clients
4. Security server for Providers.