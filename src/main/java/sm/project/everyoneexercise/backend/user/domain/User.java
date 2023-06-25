package sm.project.everyoneexercise.backend.user.domain;

import lombok.Builder;

@Builder
public record User(String userId, String nickname, String password, String phoneNumber, Boolean autoLogin) {
}
