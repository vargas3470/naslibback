package nas.test.library.iam.domain.services;

import nas.test.library.iam.domain.model.aggregates.User;
import nas.test.library.iam.domain.model.queries.GetAllUsersQuery;
import nas.test.library.iam.domain.model.queries.GetUserByIdQuery;
import nas.test.library.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);
}

