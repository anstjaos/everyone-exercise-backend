package sm.project.everyoneexercise.backend.user.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Service
class UserRegisterService implements RegisterUserUseCase {
    private final RegisterUserPort registerUserPort;

    @Override
    public User registerUser(RegisterUserCommand registerUserCommand) {
        return registerUserPort.registerUser(registerUserCommand);
    }
}
