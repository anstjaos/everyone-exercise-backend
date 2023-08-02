package sm.project.everyoneexercise.backend.user.application.port.in;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface ReadUserUseCase {
    Mono<User> readUser(String userId);
}
