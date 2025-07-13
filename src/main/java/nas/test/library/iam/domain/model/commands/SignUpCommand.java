package nas.test.library.iam.domain.model.commands;

import java.util.Set;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */

public record SignUpCommand(String name, String lastname, String username, String email, String password) {
}