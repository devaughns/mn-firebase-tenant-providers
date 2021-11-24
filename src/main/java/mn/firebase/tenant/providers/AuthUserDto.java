package mn.firebase.tenant.providers;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AuthUserDto {

    private String authUri;
    private String sessionId;
    private String providerId;

    public AuthUserDto() {
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
