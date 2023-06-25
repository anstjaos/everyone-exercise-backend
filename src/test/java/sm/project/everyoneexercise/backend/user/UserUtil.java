package sm.project.everyoneexercise.backend.user;

import sm.project.everyoneexercise.backend.user.adapter.out.persistence.UserJpaEntity;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

public class UserUtil {
    public static RegisterUserCommand createRegisterUserCommand() {
        return RegisterUserCommand.builder()
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .phoneNumber("phoneNumber")
                .autoLogin(false)
                .build();
    }

    public static User createUser(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .userId(registerUserCommand.userId())
                .nickname(registerUserCommand.nickname())
                .password(registerUserCommand.password())
                .phoneNumber(registerUserCommand.phoneNumber())
                .autoLogin(registerUserCommand.autoLogin())
                .build();
    }

    public static User createUser(UserJpaEntity userJpaEntity) {
        return User.builder()
                .userId(userJpaEntity.getUserId())
                .nickname(userJpaEntity.getNickname())
                .password(userJpaEntity.getPassword())
                .phoneNumber(userJpaEntity.getPhoneNumber())
                .autoLogin(userJpaEntity.getAutoLogin())
                .build();
    }

    public static User createUser() {
        return User.builder()
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .phoneNumber("phoneNumber")
                .autoLogin(false)
                .build();
    }

    public static UserJpaEntity createUserJpaEntity(RegisterUserCommand registerUserCommand) {
        return new UserJpaEntity(registerUserCommand.userId(),
                registerUserCommand.nickname(),
                registerUserCommand.password(),
                registerUserCommand.phoneNumber(),
                registerUserCommand.autoLogin());
    }

    public static UserJpaEntity createUserJpaEntity() {
        return new UserJpaEntity("userId", "nickname", "password", "phoneNumber", false);
    }
}
