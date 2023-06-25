package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private RegisterUserPort registerUserPort;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_success() {
        var registerUserCommand = UserUtil.createRegisterUserCommand();
        var user = UserUtil.createUser(registerUserCommand);

        when(registerUserPort.registerUser(registerUserCommand)).thenReturn(user);

        var resultUser = userService.registerUser(registerUserCommand);

        assertThat(resultUser.userId()).isEqualTo(registerUserCommand.userId());
        assertThat(resultUser.nickname()).isEqualTo(registerUserCommand.nickname());
        assertThat(resultUser.password()).isEqualTo(registerUserCommand.password());
        assertThat(resultUser.phoneNumber()).isEqualTo(registerUserCommand.phoneNumber());
        assertThat(resultUser.autoLogin()).isEqualTo(registerUserCommand.autoLogin());
    }
}