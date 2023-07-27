package sm.project.everyoneexercise.backend.user.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sm.project.everyoneexercise.backend.user.application.port.in.DeleteUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.out.DeleteUserPort;

@AllArgsConstructor
@Service
public class UserDeleteService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;

    @Override
    public void deleteUser(String userId) {
        deleteUserPort.deleteUser(userId);
    }
}
