package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.UpdateUserPort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUpdateServiceTest {
    @Mock
    private UpdateUserPort updateUserPort;

    @InjectMocks
    private UserUpdateService userUpdateService;

    @Test
    void updateUser_success() {
        var userId = "user_id";
        var updateUserCommand = UserUtil.createUpdateUserCommand();
        var user = UserUtil.createUser(updateUserCommand);

        when(updateUserPort.updateUser(userId, updateUserCommand)).thenReturn(Mono.just(user));

        var updateUserResult = userUpdateService.updateUser(userId, updateUserCommand);

        StepVerifier.create(updateUserResult)
                .expectNextMatches(updateUser -> updateUser.nickname().equals(updateUserCommand.nickname())
                        && updateUser.password().equals(updateUserCommand.password())
                        && updateUser.phoneNumber().equals(updateUserCommand.phoneNumber())
                        && updateUser.autoLogin().equals(updateUserCommand.autoLogin()))
                .verifyComplete();
    }
}