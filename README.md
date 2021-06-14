## How to Run:

1. Update `src/main/resources/application.yml` to include your GCP project ID and to point to a credential file that has proper GCP credentials to list providers for a tenant
2. Run with `./gradlew run`
3. Issue a GET request to `http://localhost:8080/providers/{tenantId}`

