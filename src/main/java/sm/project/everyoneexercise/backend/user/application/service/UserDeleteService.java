package sm.project.everyoneexercise.backend.user.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.user.application.port.in.DeleteUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.out.DeleteUserPort;

@AllArgsConstructor
@Service
public class UserDeleteService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;

    @Override
    public Mono<Void> deleteUser(String userId) {
        return deleteUserPort.deleteUser(userId);
    }
}
