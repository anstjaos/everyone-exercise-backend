package sm.project.everyoneexercise.backend.user.application.port.in;

import sm.project.everyoneexercise.backend.user.domain.User;

public interface RegisterUserUseCase {
    User registerUser(RegisterUserCommand registerUserCommand);
}
