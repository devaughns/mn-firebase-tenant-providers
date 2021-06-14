## How to Run:

1. Update `application.yml` to use a projectId and point to a credential file that has proper GCP credentials to list providers for a tenant
2. Run with `./gradlew run`
3. Issue a GET request to `http://localhost:8080/providers/{tenantId}`

