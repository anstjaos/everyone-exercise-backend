package sm.project.everyoneexercise.backend.user.adapter.in;

import lombok.Builder;

@Builder
public record UpdateUserRequest(String nickname, String password, String phoneNumber, Boolean autoLogin) {
}
