package nas.test.library.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.token.TokenService;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface BearerTokenService extends TokenService {
    String generateToken(String username);

    String getBearerTokenFrom(HttpServletRequest request);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}
