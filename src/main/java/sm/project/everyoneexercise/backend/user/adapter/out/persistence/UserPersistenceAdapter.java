package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.out.DeleteUserPort;
import sm.project.everyoneexercise.backend.user.application.port.out.ReadUserPort;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;
import sm.project.everyoneexercise.backend.user.application.port.out.UpdateUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Component
class UserPersistenceAdapter implements RegisterUserPort, ReadUserPort, UpdateUserPort, DeleteUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public Mono<User> registerUser(RegisterUserCommand registerUserCommand) {
        var userEntity = userMapper.mapCommandToEntity(registerUserCommand);

        return userRepository.save(userEntity)
                .map(userMapper::mapEntityToDomainEntity);
    }

    @Override
    public User readUserByUserId(String userId) {
//        var userJpaEntity = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User is not exists. user id = " + userId));
//
//        return userMapper.mapEntityToDomainEntity(userJpaEntity);
        return null;
    }

    @Override
    @Transactional
    public Mono<User> updateUser(String userId, UpdateUserCommand updateUserCommand) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UserNotFoundException("User is not found. User id : " + userId))))
                .flatMap(userJpaEntity -> {
                    userJpaEntity.updateUser(updateUserCommand);
                    return Mono.just(userMapper.mapEntityToDomainEntity(userJpaEntity));
                });
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
//        userRepository.deleteById(userId);
        return;
    }
}
