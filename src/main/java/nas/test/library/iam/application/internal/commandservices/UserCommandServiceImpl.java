package nas.test.library.iam.application.internal.commandservices;

import nas.test.library.iam.application.internal.outboundservices.hashing.HashingService;
import nas.test.library.iam.application.internal.outboundservices.tokens.TokenService;
import nas.test.library.iam.domain.model.aggregates.User;
import nas.test.library.iam.domain.model.commands.SignUpCommand;
import nas.test.library.iam.domain.model.commands.SignInCommand;
import nas.test.library.iam.domain.services.UserCommandService;
import nas.test.library.iam.infrastructure.persistence.jps.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;


    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;

    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");




        var user = new User(command.name(), command.lastname(), command.username(), command.email(), hashingService.encode(command.password()));
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }


    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));

    }

}
