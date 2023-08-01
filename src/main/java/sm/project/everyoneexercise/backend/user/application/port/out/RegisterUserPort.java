package sm.project.everyoneexercise.backend.user.application.port.out;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface RegisterUserPort {
    Mono<User> registerUser(RegisterUserCommand registerUserCommand);
}
