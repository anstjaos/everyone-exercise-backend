package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;

import java.util.Optional;

@Document(collection = "user")
@Getter
public class UserEntity {

    @Id
    private String userId;
    private String nickname;
    private String password;
    private String phoneNumber;
    private Boolean autoLogin;

    public UserEntity() {}

    @Builder
    public UserEntity(String userId, String nickname, String password, String phoneNumber, Boolean autoLogin) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.autoLogin = autoLogin;
    }

    public void updateUser(UpdateUserCommand updateUserCommand) {
        // TODO : if 문으로 수정
        Optional.ofNullable(updateUserCommand.nickname())
                .ifPresent(nickname -> this.nickname = nickname);

        Optional.ofNullable(updateUserCommand.password())
                .ifPresent(password -> this.password = password);

        Optional.ofNullable(updateUserCommand.phoneNumber())
                .ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);

        Optional.ofNullable(updateUserCommand.autoLogin())
                .ifPresent(autoLogin -> this.autoLogin = autoLogin);
    }
}
