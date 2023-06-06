package sm.project.everyoneexercise.backend.user.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RegisterUserCommand(@NotNull String userId, @NotNull String nickname, @NotNull String password,
                                  String phoneNumber, Boolean autoLogin) {
}
