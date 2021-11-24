package mn.firebase.tenant.providers;

import java.util.List;

public class IdpResponse {

    String federatedId;
    String providerId;
    String email;
    boolean emailVerified;
    String firstName;
    String fullName;
    String lastName;
    String nickName;
    String language;
    String timeZone;
    String photoUrl;
    String dateOfBirth;
    String originalEmail;
    String localId;
    boolean emailRecycled;
    String displayName;
    String idToken;
    String context;
    List<String> verifiedProvider;
    boolean needConfirmation;
    String oauthAccessToken;
    String oauthRefreshToken;
    String oauthExpireIn;
    String oauthAuthorizationCode;
    String oauthTokenSecret;
    String refreshToken;
    String expiresIn;
    String oauthIdToken;
    String screenName;
    String rawUserInfo;
    String errorMessage;
    boolean isNewUser;
    String pendingToken;
    String tenantId;
    String mfaPendingCredential;
    List<Object> mfaInfo;

    public String getFederatedId() {
        return federatedId;
    }

    public void setFederatedId(String federatedId) {
        this.federatedId = federatedId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOriginalEmail() {
        return originalEmail;
    }

    public void setOriginalEmail(String originalEmail) {
        this.originalEmail = originalEmail;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public boolean isEmailRecycled() {
        return emailRecycled;
    }

    public void setEmailRecycled(boolean emailRecycled) {
        this.emailRecycled = emailRecycled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<String> getVerifiedProvider() {
        return verifiedProvider;
    }

    public void setVerifiedProvider(List<String> verifiedProvider) {
        this.verifiedProvider = verifiedProvider;
    }

    public boolean isNeedConfirmation() {
        return needConfirmation;
    }

    public void setNeedConfirmation(boolean needConfirmation) {
        this.needConfirmation = needConfirmation;
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public String getOauthRefreshToken() {
        return oauthRefreshToken;
    }

    public void setOauthRefreshToken(String oauthRefreshToken) {
        this.oauthRefreshToken = oauthRefreshToken;
    }

    public String getOauthExpireIn() {
        return oauthExpireIn;
    }

    public void setOauthExpireIn(String oauthExpireIn) {
        this.oauthExpireIn = oauthExpireIn;
    }

    public String getOauthAuthorizationCode() {
        return oauthAuthorizationCode;
    }

    public void setOauthAuthorizationCode(String oauthAuthorizationCode) {
        this.oauthAuthorizationCode = oauthAuthorizationCode;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getOauthIdToken() {
        return oauthIdToken;
    }

    public void setOauthIdToken(String oauthIdToken) {
        this.oauthIdToken = oauthIdToken;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getRawUserInfo() {
        return rawUserInfo;
    }

    public void setRawUserInfo(String rawUserInfo) {
        this.rawUserInfo = rawUserInfo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public String getPendingToken() {
        return pendingToken;
    }

    public void setPendingToken(String pendingToken) {
        this.pendingToken = pendingToken;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getMfaPendingCredential() {
        return mfaPendingCredential;
    }

    public void setMfaPendingCredential(String mfaPendingCredential) {
        this.mfaPendingCredential = mfaPendingCredential;
    }

    public List<Object> getMfaInfo() {
        return mfaInfo;
    }

    public void setMfaInfo(List<Object> mfaInfo) {
        this.mfaInfo = mfaInfo;
    }
}
