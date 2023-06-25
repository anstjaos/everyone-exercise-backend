package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.out.ReadUserPort;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Component
class UserPersistenceAdapter implements RegisterUserPort, ReadUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User registerUser(RegisterUserCommand registerUserCommand) {
        var userEntity = userMapper.mapCommandToEntity(registerUserCommand);
        var userJpaEntity =  userRepository.save(userEntity);

        return userMapper.mapEntityToDomainEntity(userJpaEntity);
    }

    @Override
    public User readUserByUserId(String userId) {
        var userJpaEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User is not exists. user id = " + userId));

        return userMapper.mapEntityToDomainEntity(userJpaEntity);
    }
}
