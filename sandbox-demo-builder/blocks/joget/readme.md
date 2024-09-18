# Joget as building block

This is a custom deployment build on top of [official Joget on Kubernetes deployment](https://dev.joget.org/community/display/DX8/Joget+on+Kubernetes). 

## Deployment

The application is expected to be deployed in the default namespace or have to do namespace adjustments.
```
helm upgrade joget ./joget --install 
```
