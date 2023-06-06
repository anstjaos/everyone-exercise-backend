package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.out.RegisterUserPort;
import sm.project.everyoneexercise.backend.user.domain.User;

@AllArgsConstructor
@Component
class UserPersistenceAdapter implements RegisterUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User registerUser(RegisterUserCommand registerUserCommand) {
        var userEntity = userMapper.mapCommandToEntity(registerUserCommand);
        var userJpaEntity =  userRepository.save(userEntity);

        return userMapper.mapEntityToDomainEntity(userJpaEntity);
    }
}
