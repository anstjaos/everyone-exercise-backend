package sm.project.everyoneexercise.backend.user.application.port.out;

import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface RegisterUserPort {
    User registerUser(RegisterUserCommand registerUserCommand);
}
