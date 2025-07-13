package nas.test.library.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface TokenService {
    String generateToken(Authentication authentication);

    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
