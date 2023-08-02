package sm.project.everyoneexercise.backend.user.application.port.out;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface ReadUserPort {
    Mono<User> readUserByUserId(String userId);
}
