package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.stereotype.Component;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

@Component
class UserMapper {

    UserEntity mapCommandToEntity(RegisterUserCommand registerUserCommand) {
        return new UserEntity(registerUserCommand.userId(),
                registerUserCommand.nickname(),
                registerUserCommand.password(),
                registerUserCommand.phoneNumber(),
                registerUserCommand.autoLogin());
    }

    User mapEntityToDomainEntity(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .autoLogin(userEntity.getAutoLogin())
                .build();
    }
}
