package myapp.conversation;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Created by racak on 03-Feb-16.
 */
public class CustomSession extends AuthenticatedWebSession {
    private static String userId;

    public CustomSession(Request request) {
        super(request);
        // TODO
        userId = "te98123";
    }

    @Override
    protected boolean authenticate(String s, String s1) {
        return true;
    }

    @Override
    public Roles getRoles() {
        return null;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userI) {
        userId = userI;
    }
}
