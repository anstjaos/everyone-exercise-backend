package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.application.port.out.UpdateUserPort;

import static org.assertj.core.api.Assertions.assertThat;
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

        when(updateUserPort.updateUser(userId, updateUserCommand)).thenReturn(user);

        var updateUser = userUpdateService.updateUser(userId, updateUserCommand);

        assertThat(updateUser.nickname()).isEqualTo(updateUserCommand.nickname());
        assertThat(updateUser.password()).isEqualTo(updateUserCommand.password());
        assertThat(updateUser.phoneNumber()).isEqualTo(updateUserCommand.phoneNumber());
        assertThat(updateUser.autoLogin()).isEqualTo(updateUserCommand.autoLogin());
    }
}