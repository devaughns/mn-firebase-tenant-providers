package mn.firebase.tenant.providers;

import com.google.firebase.auth.UserRecord;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Post("/auth/{tenantId}")
    @ExecuteOn(TaskExecutors.IO)
    public UserRecord createUserInTenant(String tenantId, @Body UserDto user) {
        return authService.createUser(tenantId, user);
    }

    @Post("/auth/authUri")
    @ExecuteOn(TaskExecutors.IO)
    public AuthUserDto createAuthUri(String tenantId, String email) {
        return authService.createUri(tenantId, email);
    }

    @Get("/auth/login")
    @ExecuteOn(TaskExecutors.IO)
    public IdpResponse loginWithIdp(String state, String code, String scope) {
        return authService.login(state, code);
    }

}
