package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String USER_ID = "user-id";

    @Test
    void saveUser_success() {
        var user = createUserEntity();

        StepVerifier.create(userRepository.deleteAll().then(userRepository.save(user)))
                .expectNextMatches(userEntity -> userEntity.getUserId().equals(user.getUserId())
                        && userEntity.getNickname().equals(user.getNickname())
                        && userEntity.getPassword().equals(user.getPassword())
                        && userEntity.getPhoneNumber().equals(user.getPhoneNumber())
                        && userEntity.getAutoLogin().equals(user.getAutoLogin()))
                .verifyComplete();
    }

    @Test
    void readUser_success() {
        var user = createUserEntity();

        Publisher<UserEntity> setup = userRepository.save(user);
        Mono<UserEntity> find = userRepository.findById(USER_ID);

        Publisher<UserEntity> composite = Mono.from(setup).then(find);

        StepVerifier.create(composite)
                .assertNext(userEntity -> {
                    assertEquals(userEntity.getNickname(), user.getNickname());
                })
                .verifyComplete();
    }

    private UserEntity createUserEntity() {
        return UserEntity.builder()
                .userId(USER_ID)
                .nickname("nickname")
                .password("password")
                .phoneNumber("phone-number")
                .autoLogin(true)
                .build();
    }
}