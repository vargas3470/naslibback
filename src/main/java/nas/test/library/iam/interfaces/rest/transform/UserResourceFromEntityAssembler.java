package nas.test.library.iam.interfaces.rest.transform;

import nas.test.library.iam.domain.model.aggregates.User;
import nas.test.library.iam.interfaces.rest.resources.UserResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getUsername());
    }
}
