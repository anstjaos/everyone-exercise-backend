package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;
import sm.project.everyoneexercise.backend.user.UserUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DirtiesContext
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

    @Test
    void readUserByUserId_success() {
        var userJpaEntity = UserUtil.createUserJpaEntity();
        var user = UserUtil.createUser(userJpaEntity);

        when(userRepository.findById(userJpaEntity.getUserId())).thenReturn(Optional.of(userJpaEntity));
        when(userMapper.mapEntityToDomainEntity(userJpaEntity)).thenReturn(user);

        var readUser = userPersistenceAdapter.readUserByUserId(userJpaEntity.getUserId());

        assertThat(readUser.userId()).isEqualTo(userJpaEntity.getUserId());
        assertThat(readUser.nickname()).isEqualTo(userJpaEntity.getNickname());
        assertThat(readUser.password()).isEqualTo(userJpaEntity.getPassword());
        assertThat(readUser.phoneNumber()).isEqualTo(userJpaEntity.getPhoneNumber());
        assertThat(readUser.autoLogin()).isEqualTo(userJpaEntity.getAutoLogin());
    }

    @Test
    void readUserByUserId_failure_notExists() {
        var userJpaEntity = UserUtil.createUserJpaEntity();

        when(userRepository.findById(userJpaEntity.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userPersistenceAdapter.readUserByUserId(userJpaEntity.getUserId()));
    }
}