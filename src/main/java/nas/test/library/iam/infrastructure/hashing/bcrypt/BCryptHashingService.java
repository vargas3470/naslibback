package nas.test.library.iam.infrastructure.hashing.bcrypt;

import nas.test.library.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {

}