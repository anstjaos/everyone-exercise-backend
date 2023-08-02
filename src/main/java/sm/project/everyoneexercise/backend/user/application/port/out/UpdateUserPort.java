package sm.project.everyoneexercise.backend.user.application.port.out;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface UpdateUserPort {
    Mono<User> updateUser(String userId, UpdateUserCommand updateUserCommand);
}
