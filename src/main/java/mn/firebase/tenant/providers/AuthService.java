package mn.firebase.tenant.providers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.multitenancy.TenantAwareFirebaseAuth;
import com.google.firebase.auth.multitenancy.TenantManager;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.exceptions.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class AuthService {

    private Logger log = LoggerFactory.getLogger(AuthService.class);
    private TenantManager manager;
    private final String API_KEY;
    @Inject
    HttpClient httpClient;

    private String sessionId;
    private String providerId;

    public AuthService(
            @Value("${gcp.project-id}") String projectId,
            GoogleCredentials creds,
            @Value("${gcp.apikey}") String api_key) {
        API_KEY = api_key;
        log.info("Initializing AuthService for project '{}'...", projectId);

        FirebaseOptions options = FirebaseOptions.builder()
                .setProjectId(projectId)
                .setCredentials(creds).build();

        FirebaseApp app = FirebaseApp.initializeApp(options);

        this.manager = FirebaseAuth.getInstance(app).getTenantManager();

        log.info("AuthService initialization complete");
    }

    public UserRecord createUser(String tenantId, UserDto user) {
        UserRecord.CreateRequest createRequest;
        UserRecord userRecord;

        createRequest = new UserRecord.CreateRequest();
        createRequest.setDisplayName(user.getDisplayName());
        createRequest.setEmail(user.getEmail());
        createRequest.setEmailVerified(true);
        createRequest.setDisabled(false);

        try {
            TenantAwareFirebaseAuth tenantAuth = manager.getAuthForTenant(tenantId);
            userRecord = tenantAuth.createUser(createRequest);
            log.debug("new user: " + userRecord.getDisplayName()+ " with ID " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            log.error("Received error creating user!", e);
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "createUser: " + e.toString());
        }

        return userRecord;
    }

    public AuthUserDto createUri(String tenantId, String email) {
        Map<String, String> body = new HashMap<>();
        body.put("identifier", email);
        body.put("continueUri", "http://localhost:5000");
        body.put("providerId", "google.com");
        body.put("oauthScope", "profile openid");
//        body.put("context", "SomethingNewCanGoHere");
//        body.put("hostedDomain", "objectcomputing.com");
        body.put("tenantId", tenantId);

        HttpResponse<CreateAuthResp> response = httpClient.toBlocking().exchange(
                HttpRequest.POST("https://identitytoolkit.googleapis.com/v1/accounts:createAuthUri?key=" + API_KEY, body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON),
                CreateAuthResp.class
        );
        Optional<CreateAuthResp> bodE = response.getBody();
        if (bodE.isPresent()) {
            CreateAuthResp resp = bodE.get();

            sessionId = resp.getSessionId();
            providerId = resp.getProviderId();

            AuthUserDto user = new AuthUserDto();
            user.setSessionId(resp.getSessionId());
            user.setAuthUri(resp.getAuthUri());
            user.setProviderId(resp.getProviderId());
            return user;
        } else  {
            return null;
        }
    }

    public IdpResponse login(String state, String code) {
        Map<String, Object> params = new HashMap<>();

        params.put("requestUri", "http://localhost:5000");
//        params.put("postBody", "providerId="+providerId+"&code="+code+"&state="+state);
        params.put("postBody", "code="+code+"&state="+state);
        params.put("sessionId", sessionId);
        params.put("returnSecureToken", true);
        params.put("returnIdpCredential", false);
        params.put("tenantId", "provider-test-zirqb");

        IdpResponse resp = new IdpResponse();
        try {
            HttpResponse<IdpResponse> response = httpClient.toBlocking().exchange(
                HttpRequest.POST("https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key=" + API_KEY, params)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON),
                IdpResponse.class
            );
            Optional<IdpResponse> optionalResp = response.getBody();
            if (optionalResp.isPresent()) {
                resp = optionalResp.get();
            }
        } catch(Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return resp;
    }

}
