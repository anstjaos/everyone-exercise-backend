package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.AbstractDbTest;

@DataMongoTest
@ActiveProfiles("test")
class UserRepositoryTest extends AbstractDbTest {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        reactiveMongoTemplate.dropCollection(UserEntity.class).subscribe();
    }

    @Test
    void saveUser_success() {
        var user = UserEntity.builder()
                .userId("user-id")
                .nickname("nickname")
                .password("password")
                .phoneNumber("phone-number")
                .autoLogin(true)
                .build();

        StepVerifier.create(userRepository.deleteAll().then(userRepository.save(user)))
                .expectNextMatches(userEntity -> userEntity.getUserId().equals(user.getUserId())
                        && userEntity.getNickname().equals(user.getNickname())
                        && userEntity.getPassword().equals(user.getPassword())
                        && userEntity.getPhoneNumber().equals(user.getPhoneNumber())
                        && userEntity.getAutoLogin().equals(user.getAutoLogin()))
                .verifyComplete();
    }
}