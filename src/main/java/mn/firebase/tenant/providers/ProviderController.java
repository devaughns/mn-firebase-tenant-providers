package mn.firebase.tenant.providers;

import java.util.Map;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/providers")
public class ProviderController {

	private ProviderService svc;

	public ProviderController(ProviderService svc) {
		this.svc = svc;
	}

	@Get("/{tenantId}")
	@ExecuteOn(TaskExecutors.IO)
	public Map<String, String> getProvidersForTenant(String tenantId) {
		return svc.getProviders(tenantId);
	}
}
