package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;
import sm.project.everyoneexercise.backend.user.UserUtil;

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
        when(userRepository.save(userJpaEntity)).thenReturn(Mono.just(userJpaEntity));
        when(userMapper.mapEntityToDomainEntity(userJpaEntity)).thenReturn(user);

        var registerUser = userPersistenceAdapter.registerUser(registerUserCommand);

        StepVerifier.create(registerUser)
                .expectNextMatches(resultUser -> resultUser.userId().equals(registerUserCommand.userId())
                        && resultUser.nickname().equals(registerUserCommand.nickname())
                        && resultUser.password().equals(registerUserCommand.password())
                        && resultUser.phoneNumber().equals(registerUserCommand.phoneNumber())
                        && resultUser.autoLogin().equals(registerUserCommand.autoLogin()))
                .verifyComplete();
    }
//
//    @Test
//    void readUserByUserId_success() {
//        var userJpaEntity = UserUtil.createUserJpaEntity();
//        var user = UserUtil.createUser(userJpaEntity);
//
//        when(userRepository.findById(userJpaEntity.getUserId())).thenReturn(Optional.of(userJpaEntity));
//        when(userMapper.mapEntityToDomainEntity(userJpaEntity)).thenReturn(user);
//
//        var readUser = userPersistenceAdapter.readUserByUserId(userJpaEntity.getUserId());
//
//        assertThat(readUser.userId()).isEqualTo(userJpaEntity.getUserId());
//        assertThat(readUser.nickname()).isEqualTo(userJpaEntity.getNickname());
//        assertThat(readUser.password()).isEqualTo(userJpaEntity.getPassword());
//        assertThat(readUser.phoneNumber()).isEqualTo(userJpaEntity.getPhoneNumber());
//        assertThat(readUser.autoLogin()).isEqualTo(userJpaEntity.getAutoLogin());
//    }
//
//    @Test
//    void readUserByUserId_failure_userNotExists() {
//        var userJpaEntity = UserUtil.createUserJpaEntity();
//
//        when(userRepository.findById(userJpaEntity.getUserId())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userPersistenceAdapter.readUserByUserId(userJpaEntity.getUserId()));
//    }
//
    @Test
    void updateUser_success() {
        var updateUserCommand = UserUtil.createUpdateUserCommand();
        var userJpaEntity = UserUtil.createUserJpaEntity();
        var user = UserUtil.createUser(updateUserCommand);

        when(userRepository.findById(userJpaEntity.getUserId())).thenReturn(Mono.just(userJpaEntity));
        when(userMapper.mapEntityToDomainEntity(userJpaEntity)).thenReturn(user);

        var updateUserResult = userPersistenceAdapter.updateUser(userJpaEntity.getUserId(), updateUserCommand);

        StepVerifier.create(updateUserResult)
                .expectNextMatches(updateUser -> updateUser.nickname().equals(updateUserCommand.nickname())
                        && updateUser.password().equals(updateUserCommand.password())
                        && updateUser.phoneNumber().equals(updateUserCommand.phoneNumber())
                        && updateUser.autoLogin().equals(updateUserCommand.autoLogin()))
                .verifyComplete();
    }

    @Test
    void updateUser_failed_userNotExists() {
        var userId = "user_id";
        var updateUserCommand = UserUtil.createUpdateUserCommand();

        when(userRepository.findById(userId)).thenReturn(Mono.empty());

        StepVerifier.create(userPersistenceAdapter.updateUser(userId, updateUserCommand))
                .expectError(UserNotFoundException.class)
                .verify();
    }
}