# DOCKER
## Building docker image:
`./gradlew bootBuildImage`

## Docker Run:
`docker run --name  paymentBBEmulator -p 15000:8080 -d bb/payment/emulator:0.0.1`