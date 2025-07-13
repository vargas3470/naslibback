package nas.test.library.iam.interfaces.rest.resources;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record SignUpResource(
        String name,
        String lastname,
        String username,
        String email,
        String password
) {}

