package mn.firebase.tenant.providers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListProviderConfigsPage;
import com.google.firebase.auth.OidcProviderConfig;
import com.google.firebase.auth.SamlProviderConfig;
import com.google.firebase.auth.multitenancy.TenantAwareFirebaseAuth;
import com.google.firebase.auth.multitenancy.TenantManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

@Singleton
public class ProviderService {

	private Logger log = LoggerFactory.getLogger(ProviderService.class);
	private TenantManager manager;

	public ProviderService(
		@Value("${gcp.project-id}") String projectId,
		GoogleCredentials creds
	) {
		log.info("Initializing ProviderService for project '{}'...", projectId);

		FirebaseOptions options = FirebaseOptions.builder()
			.setProjectId(projectId)
			.setCredentials(creds).build();

		FirebaseApp app = FirebaseApp.initializeApp(options);

		this.manager = FirebaseAuth.getInstance(app).getTenantManager();

		log.info("ProviderService initialization complete");
	}

	public Map<String, String> getProviders(String tenantId) {
		Map<String, String> result = new HashMap<>();

		log.debug("Retrieving providers for tenant: '{}'...", tenantId);

		try {
			TenantAwareFirebaseAuth auth = manager.getAuthForTenant(tenantId);

			log.debug("Retrieving OIDC Providers...");

			ListProviderConfigsPage<OidcProviderConfig> providerConfigPage = auth.listOidcProviderConfigs(null);

			boolean hasMore = true;

			while(hasMore) {
					for (OidcProviderConfig c : providerConfigPage.getValues()) {
							log.debug("Found OIDC Provider: {}", c.getDisplayName());
							result.put(c.getProviderId(), c.getDisplayName());
					}

					hasMore = providerConfigPage.hasNextPage();
					providerConfigPage = providerConfigPage.getNextPage();
			}

			log.debug("Retrieving SAML Providers...");

			ListProviderConfigsPage<SamlProviderConfig> samlConfigPage = auth.listSamlProviderConfigs(null);

			hasMore = true;

			while(hasMore) {
					for (SamlProviderConfig c : samlConfigPage.getValues()) {
							log.debug("Found SAML Provider: {}", c.getDisplayName());
							result.put(c.getProviderId(), c.getDisplayName());
					}

					hasMore = samlConfigPage.hasNextPage();
					samlConfigPage = samlConfigPage.getNextPage();
			}

			log.debug("Done retrieving providers.");

		} catch (Exception e) {
			log.error("Received error retrieving providers!", e);
			throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Received error retrieving providers!");
		}

		return result;
	}
}
