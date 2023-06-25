package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.ReadUserPort;

import static org.assertj.core.api.Assertions.assertThat;
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

        when(readUserPort.readUserByUserId(user.userId())).thenReturn(user);

        var readUser = userReadService.readUser(user.userId());

        assertThat(readUser.userId()).isEqualTo(user.userId());
        assertThat(readUser.nickname()).isEqualTo(user.nickname());
        assertThat(readUser.password()).isEqualTo(user.password());
        assertThat(readUser.phoneNumber()).isEqualTo(user.phoneNumber());
        assertThat(readUser.autoLogin()).isEqualTo(user.autoLogin());
    }
}