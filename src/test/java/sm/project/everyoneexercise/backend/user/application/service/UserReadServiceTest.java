package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.ReadUserPort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReadServiceTest {
    @Mock
    private ReadUserPort readUserPort;

    @InjectMocks
    private UserReadService userReadService;

    @Test
    void readUser_success() {
        var user = UserUtil.createUser();

        when(readUserPort.readUserByUserId(user.userId())).thenReturn(Mono.just(user));

        var readUserResult = userReadService.readUser(user.userId());

        StepVerifier.create(readUserResult)
                .expectNextMatches(readUser -> readUser.userId().equals(user.userId())
                        && readUser.nickname().equals(user.nickname())
                        && readUser.password().equals(user.password())
                        && readUser.phoneNumber().equals(user.phoneNumber())
                        && readUser.autoLogin().equals(user.autoLogin()))
                .verifyComplete();
    }
}