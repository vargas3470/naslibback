package nas.test.library.iam.interfaces.rest.transform;

import nas.test.library.iam.domain.model.aggregates.User;
import nas.test.library.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}