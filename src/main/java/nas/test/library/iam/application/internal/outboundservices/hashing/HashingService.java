package nas.test.library.iam.application.internal.outboundservices.hashing;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}

