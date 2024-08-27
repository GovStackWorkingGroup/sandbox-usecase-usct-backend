# CI CD

## Build image & Deployment
Build image process of creating image and installing Payment Builing Block EMULATOR in EKS cluster.

### CI contexts

| Context     | Description                                                                                     |
|:------------|:------------------------------------------------------------------------------------------------|
| sandbox-dev | Context containing all needed env-vars for image creation and deployment to sandbox-dev cluster |
| playground  | Context containing all needed env-vars for image creation and deployment to playground cluster  |

### Circle CI pipeline parameters:

| Name               | Type     | Default                          | Effect                                                                                           |
|--------------------|----------|----------------------------------|--------------------------------------------------------------------------------------------------|
| namespace          | string   | digital-registries-bb-emulator   | defines the namespace for the deployment in k8s cluster                                          |
| playground         | boolean  | false                            | defines that build and deploy should be executed to playground environment                       |
| image              | string   | "bb/digital-registries/emulator" | base path of the image in the respective environment ECR                                         |
| force_deploy       | boolean  | false                            | used to force-deploy on dev cluster when branch is not main                                      |
| emulator-db-flush  | boolean  | false                            | to install the emulator in development mode (on restart to reinit the database in initial state) |

### Circle CI use-cases:

| Use-case                                       | Description                                                                                  | Branch    | Param Values                  |
|------------------------------------------------|----------------------------------------------------------------------------------------------|-----------|-------------------------------|
| Build image in dev environment                 | Builds image and uploads it to dev env                                                       | Not main  | -                             |
| Build & Deploy image in dev environment        | Builds image, uploads it to dev env and deploys the application to dev cluster               | main      | -                             |
| Build & Deploy image in dev environment        | Builds image, uploads it to dev env and deploys the application to dev cluster               | Not main  | "force_deploy" set to "TRUE"  |
| Build & Deploy image in playground environment | Builds image, uploads it to playground env and deploys the application to playground cluster | main      | "playground" set to "TRUE"    |

* "emulator-db-flush" pipeline parameter can be set to "TRUE" for any of the use-cases.

### Circle CI Deploy Workflow:

To run follow those steps:

1. navigate to project in CircleCI
2. select branch from the dropdown
3. select "Trigger pipeline" action
4. Apply pipeline params if needed based on [Circle CI use-cases](#circle-ci-use-cases)
5. Then trigger the pipeline