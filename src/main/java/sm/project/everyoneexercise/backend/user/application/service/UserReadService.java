package sm.project.everyoneexercise.backend.user.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sm.project.everyoneexercise.backend.user.application.port.in.ReadUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.out.ReadUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Slf4j
class UserReadService implements ReadUserUseCase {
    private final ReadUserPort readUserPort;

    @Override
    public User readUser(String userId) {
        return readUserPort.readUserByUserId(userId);
    }
}
