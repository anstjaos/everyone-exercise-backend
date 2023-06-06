package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sm.project.everyoneexercise.backend.user.UserUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPersistenceAdapterTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;

    @Test
    void registerUser_success() {
        var registerUserCommand = UserUtil.createRegisterUserCommand();
        var userJpaEntity = UserUtil.createUserJpaEntity(registerUserCommand);
        var user = UserUtil.createUser(registerUserCommand);

        when(userMapper.mapCommandToEntity(registerUserCommand)).thenReturn(userJpaEntity);
        when(userRepository.save(userJpaEntity)).thenReturn(userJpaEntity);
        when(userMapper.mapEntityToDomainEntity(userJpaEntity)).thenReturn(user);

        var resultUser = userPersistenceAdapter.registerUser(registerUserCommand);

        assertThat(resultUser.userId()).isEqualTo(registerUserCommand.userId());
        assertThat(resultUser.nickname()).isEqualTo(registerUserCommand.nickname());
        assertThat(resultUser.password()).isEqualTo(registerUserCommand.password());
        assertThat(resultUser.phoneNumber()).isEqualTo(registerUserCommand.phoneNumber());
        assertThat(resultUser.autoLogin()).isEqualTo(registerUserCommand.autoLogin());
    }
}