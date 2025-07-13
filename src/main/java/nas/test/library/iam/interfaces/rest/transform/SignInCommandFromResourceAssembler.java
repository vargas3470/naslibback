package nas.test.library.iam.interfaces.rest.transform;

import nas.test.library.iam.domain.model.commands.SignInCommand;
import nas.test.library.iam.interfaces.rest.resources.SignInResource;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}