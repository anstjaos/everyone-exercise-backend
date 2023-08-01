package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRegisterServiceTest {
    @Mock
    private RegisterUserPort registerUserPort;

    @InjectMocks
    private UserRegisterService userRegisterService;

    @Test
    void registerUser_success() {
        var registerUserCommand = UserUtil.createRegisterUserCommand();
        var user = UserUtil.createUser(registerUserCommand);

        when(registerUserPort.registerUser(registerUserCommand)).thenReturn(Mono.just(user));

        var registerUser = userRegisterService.registerUser(registerUserCommand);

        StepVerifier.create(registerUser)
                .expectNextMatches(resultUser -> resultUser.userId().equals(registerUserCommand.userId())
                        && resultUser.nickname().equals(registerUserCommand.nickname())
                        && resultUser.password().equals(registerUserCommand.password())
                        && resultUser.phoneNumber().equals(registerUserCommand.phoneNumber())
                        && resultUser.autoLogin().equals(registerUserCommand.autoLogin()))
                .verifyComplete();
    }
}