package nas.test.library.iam.application.internal.queryservices;

import nas.test.library.iam.domain.model.aggregates.User;
import nas.test.library.iam.domain.model.queries.GetAllUsersQuery;
import nas.test.library.iam.domain.model.queries.GetUserByIdQuery;
import nas.test.library.iam.domain.model.queries.GetUserByUsernameQuery;
import nas.test.library.iam.domain.services.UserQueryService;
import nas.test.library.iam.infrastructure.persistence.jps.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
