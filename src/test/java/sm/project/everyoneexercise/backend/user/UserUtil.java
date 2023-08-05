package sm.project.everyoneexercise.backend.user;

import sm.project.everyoneexercise.backend.user.adapter.out.persistence.UserEntity;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;
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

    public static User createUser(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .autoLogin(userEntity.getAutoLogin())
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

    public static User createUser(UpdateUserCommand updateUserCommand) {
        return User.builder()
                .nickname(updateUserCommand.nickname())
                .password(updateUserCommand.password())
                .phoneNumber(updateUserCommand.phoneNumber())
                .autoLogin(updateUserCommand.autoLogin())
                .build();
    }

    public static UserEntity createUserJpaEntity(RegisterUserCommand registerUserCommand) {
        return new UserEntity(registerUserCommand.userId(),
                registerUserCommand.nickname(),
                registerUserCommand.password(),
                registerUserCommand.phoneNumber(),
                registerUserCommand.autoLogin());
    }

    public static UserEntity createUserJpaEntity() {
        return new UserEntity("userId", "nickname", "password", "phoneNumber", false);
    }

    public static UpdateUserCommand createUpdateUserCommand() {
        return UpdateUserCommand.builder()
                .nickname("change-nickname")
                .password("change-password")
                .phoneNumber("change-phoneNumber")
                .autoLogin(false)
                .build();
    }
}
