# Mosip related docs

Step 1: Partner onboarding
Step 2: OIDC client creation
endpoint- /client-mgmt/oidc-client
url- {esignet_service_base_url}/client-mgmt/oidc-client

Step 3: redirect to esignet(when you click on 'sign in with esignet' for example to check person exist in citizen database)
OIDC endpoint- /authorize
url- {esignet_UI_base_url}/authorize
browser redirect to this endpoint with the required details, after successful validation of all the details this will further redirect you to the /login endpoint of esignet from there you can start  the authentication of the individual.

After successful authentication you will be redirected back to your portal(SRIS for example) with the authorization code(needed to get access token in the next step).The redirect/landing page will depend upon what is passed in the redirect uri parameter in the first step.
Step 4: get the access and id token
endpoint- /oauth/token
url- {esignet_service_base_url}/oauth/token
call this API endpoint step to get access and id token needed for getting user information in the next step.

Step 5: get user information
endpoint- /oidc/userinfo
url- {esignet_service_base_url}/oidc/userinfo
call this API endpoint to get the user info.