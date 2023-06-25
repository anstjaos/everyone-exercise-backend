package sm.project.everyoneexercise.backend.user.adapter.in;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterUserRequestTest {
    private Validator validator;

    @BeforeEach
    void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validateRegisterUserRequest_failure_userIdEmpty() {
        var requestUserIdEmpty = RegisterUserRequest.builder()
                .userId("")
                .nickname("nickname")
                .password("password")
                .phoneNumber("")
                .autoLogin(false);

        var constraintViolations = validator.validate(requestUserIdEmpty);

        for (var next : constraintViolations) {
            assertEquals("User id is mandatory.", next.getMessage());
        }
    }

    @Test
    void validateRegisterUserRequest_failure_nicknameEmpty() {
        var requestNicknameEmpty = RegisterUserRequest.builder()
                .userId("userId")
                .nickname("")
                .password("password")
                .phoneNumber("")
                .autoLogin(false);

        var constraintViolations = validator.validate(requestNicknameEmpty);

        for (var next : constraintViolations) {
            assertEquals("Nickname is mandatory.", next.getMessage());
        }
    }

    @Test
    void validateRegisterUserRequest_failure_passwordEmpty() {
        var requestPasswordEmpty = RegisterUserRequest.builder()
                .userId("userId")
                .nickname("nickname")
                .password("")
                .phoneNumber("")
                .autoLogin(false);

        var constraintViolations = validator.validate(requestPasswordEmpty);

        for (var next : constraintViolations) {
            assertEquals("Password is mandatory.", next.getMessage());
        }
    }

    @Test
    void validateRegisterUserRequest_success() {
        var validRequest = RegisterUserRequest.builder()
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .phoneNumber("")
                .autoLogin(false);

        var constraintViolations = validator.validate(validRequest);

        assertEquals(0, constraintViolations.size());
    }
}