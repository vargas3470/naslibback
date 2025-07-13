package nas.test.library.iam.interfaces.rest.transform;

import nas.test.library.iam.domain.model.commands.SignUpCommand;
import nas.test.library.iam.interfaces.rest.resources.SignUpResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        String name = resource.username();
        String lastname = resource.username();
        String email = resource.username() + "@nas.com";
        String password = resource.password();

        return new SignUpCommand(name, lastname, resource.username(), email, password);
    }
}

