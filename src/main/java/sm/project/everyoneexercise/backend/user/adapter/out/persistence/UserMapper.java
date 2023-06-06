package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.stereotype.Component;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

@Component
class UserMapper {

    UserJpaEntity mapCommandToEntity(RegisterUserCommand registerUserCommand) {
        return new UserJpaEntity(registerUserCommand.userId(),
                registerUserCommand.nickname(),
                registerUserCommand.password(),
                registerUserCommand.phoneNumber(),
                registerUserCommand.autoLogin());
    }

    User mapEntityToDomainEntity(UserJpaEntity userJpaEntity) {
        return User.builder()
                .userId(userJpaEntity.getUserId())
                .nickname(userJpaEntity.getNickname())
                .password(userJpaEntity.getPassword())
                .phoneNumber(userJpaEntity.getPhoneNumber())
                .autoLogin(userJpaEntity.getAutoLogin())
                .build();
    }
}
