package sm.project.everyoneexercise.backend.user.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.out.UpdateUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Service
public class UserUpdateService implements UpdateUserUseCase {
    private final UpdateUserPort updateUserPort;

    @Override
    public Mono<User> updateUser(String userId, UpdateUserCommand updateUserCommand) {
        return updateUserPort.updateUser(userId, updateUserCommand);
    }
}
