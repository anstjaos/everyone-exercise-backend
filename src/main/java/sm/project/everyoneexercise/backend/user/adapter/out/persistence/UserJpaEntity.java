package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;

import java.util.Optional;

@Entity
@Table(name = "User")
@Getter
public class UserJpaEntity {

    @Id
    private String userId;
    private String nickname;
    private String password;
    private String phoneNumber;
    private Boolean autoLogin;

    public UserJpaEntity() {}

    public UserJpaEntity(String userId, String nickname, String password, String phoneNumber, Boolean autoLogin) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.autoLogin = autoLogin;
    }

    public void updateUser(UpdateUserCommand updateUserCommand) {
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
