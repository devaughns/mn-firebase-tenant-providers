package mn.firebase.tenant.providers;

import io.micronaut.core.annotation.Introspected;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Introspected
public class CreateAuthResp {

    String kind;
    String authUri;
    boolean registered;
    String providerId;
    boolean forExistingProvider;
    boolean captchaRequired;
    String sessionId;
    List<String> signingMethods;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getState() {
        List<NameValuePair> stateList = Objects.requireNonNull(getParams())
            .stream()
                .filter(it -> it.getName().equals("state"))
                .collect(Collectors.toList());
        return stateList.size() > 0 ? stateList.get(0).getValue() : null;
    }

    public String getCode() {
        return "";
    }

    private List<NameValuePair> getParams() {
        try {
            return URLEncodedUtils.parse(new URI(getAuthUri()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

//    https://accounts.google.com/o/oauth2/auth
//    ?response_type=code
//    &client_id=690576165389-ke3h9g5m5fq2e5olbamhrn2iq7ovns9c.apps.googleusercontent.com
//    &redirect_uri=http://localhost:5000
//    &state=AMbdmDkklop18H8-Gwa7_qzPAxfYfYuBU4fHoSWR3sVyyyyupiSicrmVekkrVM0qGA_kGXr-LGqQn0MmTb-tVruvpYcUrl9rwC_-B5O5KgKMOzgPUv9I9OhuW4fc3OwCI15QKm5aRJIoD0POSe1JuN0myznGlxUx93cFQbVucckx1VGAH9yNfbe4TS7n3pItS_7E0f8FT7wv-2B6_L4OlhBsIDu2fWORPWHuMexQ5GxTsDPKMFWUODlSJmxTuQtYs793zFFDiN6n4o1TrEaA6h1A3675qw0z99QOxO7sW0zHeOx-I1M26UWFlB-QQGE_6HMeWHp2N5E1J6209KSI9kloXCAYW2PEHIrJgz7K_siE26ky7puG20dr6__VBURMrQ
//    &scope=openid+https://www.googleapis.com/auth/userinfo.email+profile+openid
//    &hd=objectcomputing.com

}
