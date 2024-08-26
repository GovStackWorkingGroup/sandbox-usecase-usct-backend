# Docker 

## Docker build image

Building docker image:
`./gradlew bootBuildImage --imageName=bb/digital-registries/emulator:latest`

## Docker run container

Docker Run:
`docker run --name  digital-registries-emulator -p 15000:8080 -d bb/digital-registries/emulator:latest`