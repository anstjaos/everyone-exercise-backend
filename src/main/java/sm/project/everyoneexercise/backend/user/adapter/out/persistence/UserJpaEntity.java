package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;

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
}
