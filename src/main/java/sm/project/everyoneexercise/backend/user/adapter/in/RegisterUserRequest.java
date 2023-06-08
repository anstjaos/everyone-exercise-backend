package sm.project.everyoneexercise.backend.user.adapter.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterUserRequest(@NotBlank(message = "User id is mandatory.") String userId,
                                  @NotBlank(message = "Nickname is mandatory.") String nickname,
                                  @NotBlank(message = "Password is mandatory.") String password,
                                  String phoneNumber, Boolean autoLogin) {
}
