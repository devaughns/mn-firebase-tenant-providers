## How to Run:

1. Update `src/main/resources/application.yml` to include your GCP project ID and to point to a credential file that has proper GCP credentials to list providers for a tenant
2. Run with `./gradlew run`
3. Issue a GET request to `http://localhost:8080/providers/{tenantId}`

TODO:
-create service that returns provider(s) based on user email
-create middle page between sign in and result


Steps:
1. user inputs email and clicks login
2. auth api takes email, looks up provider and uses provider id to call `createAuthUri`
3. set `continueUri` to be the middle page
    - note, this will need to be set in the Google Admin/Identity Platform page
4. sign in with google
5. after landing on the middle page, persist sessionId, code, state, any other params from url
6. call login endpoint with items
7. perform `signInWithIdp` on the backend
8. return jwt and expiresIn from  response