package nas.test.library.iam.infrastructure.authorization.sfs.services;

import nas.test.library.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import nas.test.library.iam.infrastructure.persistence.jps.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));

        return UserDetailsImpl.build(user);

    }
}