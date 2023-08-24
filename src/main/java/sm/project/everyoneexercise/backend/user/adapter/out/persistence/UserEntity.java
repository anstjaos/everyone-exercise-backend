package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;

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
        if (StringUtils.isNotBlank(updateUserCommand.nickname())) {
            this.nickname = updateUserCommand.nickname();
        }

        if (StringUtils.isNotBlank(updateUserCommand.password())) {
            this.password = updateUserCommand.password();
        }

        if (StringUtils.isNotBlank(updateUserCommand.phoneNumber())) {
            this.phoneNumber = updateUserCommand.phoneNumber();
        }

        if (updateUserCommand.autoLogin() != null) {
            this.autoLogin = updateUserCommand.autoLogin();
        }
    }
}
