package sm.project.everyoneexercise.backend.user.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;

@Builder
public record RegisterUserCommand(@NotNull String userId, @NotNull String nickname, @NotNull String password,
                                  String phoneNumber, Boolean autoLogin) {

    public static RegisterUserCommand mapRequestToCommand(RegisterUserRequest registerUserRequest) {
        return RegisterUserCommand.builder()
                .userId(registerUserRequest.userId())
                .nickname(registerUserRequest.nickname())
                .password(registerUserRequest.password())
                .phoneNumber(registerUserRequest.phoneNumber())
                .autoLogin(registerUserRequest.autoLogin())
                .build();
    }
}
