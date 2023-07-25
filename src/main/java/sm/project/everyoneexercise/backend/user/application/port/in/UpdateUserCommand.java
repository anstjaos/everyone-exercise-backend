package sm.project.everyoneexercise.backend.user.application.port.in;

import lombok.Builder;
import sm.project.everyoneexercise.backend.user.adapter.in.UpdateUserRequest;

import java.util.Optional;

@Builder
public record UpdateUserCommand(String nickname, String password, String phoneNumber, Boolean autoLogin) {
    public static UpdateUserCommand mapRequestToCommand(UpdateUserRequest request) {
        var userCommandBuilder = UpdateUserCommand.builder();

        Optional.ofNullable(request.nickname())
                .ifPresent(userCommandBuilder::nickname);

        Optional.ofNullable(request.password())
                .ifPresent(userCommandBuilder::password);

        Optional.ofNullable(request.phoneNumber())
                .ifPresent(userCommandBuilder::phoneNumber);

        Optional.ofNullable(request.autoLogin())
                .ifPresent(userCommandBuilder::autoLogin);

        return userCommandBuilder.build();
    }
}
