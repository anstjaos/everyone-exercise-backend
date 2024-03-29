package sm.project.everyoneexercise.backend.user.adapter.in.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.common.ResponseCode;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.adapter.in.UpdateUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.*;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
class UserControllerTest {

    @MockBean
    private RegisterUserUseCase registerUserUseCase;

    @MockBean
    private ReadUserUseCase readUserUseCase;

    @MockBean
    private UpdateUserUseCase updateUserUseCase;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void registerUser_failure_validateFailed() {
        var registerUserRequest = RegisterUserRequest.builder()
                .phoneNumber("")
                .autoLogin(false)
                .build();

        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(registerUserRequest), RegisterUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(false)
                .jsonPath("$.header.statusCode").isEqualTo(ResponseCode.VALIDATE_FAILED.getStatusCode())
                .jsonPath("$.header.message").value(Matchers.containsString("User id is mandatory."))
                .jsonPath("$.header.message").value(Matchers.containsString("Nickname is mandatory."));
    }

    @Test
    void registerUser_success() {
        var registerUserRequest = RegisterUserRequest.builder()
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .phoneNumber("phoneNumber")
                .autoLogin(false)
                .build();

        var registerUserCommand = RegisterUserCommand.mapRequestToCommand(registerUserRequest);

        when(registerUserUseCase.registerUser(registerUserCommand)).thenReturn(Mono.just(UserUtil.createUser(registerUserCommand)));

        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(registerUserRequest), RegisterUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(true)
                .jsonPath("$.header.statusCode").isEqualTo(200);
    }

    @Test
    void readUser_success() {
        var user = UserUtil.createUser();

        when(readUserUseCase.readUser(user.userId())).thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/users/" + user.userId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(true)
                .jsonPath("$.body.userId").isEqualTo(user.userId());
    }

    @Test
    void readUser_failed_userNotFoundException() {
        var userId = "user_id";
        when(readUserUseCase.readUser(userId)).thenReturn(Mono.error(new UserNotFoundException("User is not found. User id : " + userId)));

        webTestClient.get()
                .uri("/users/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(false)
                .jsonPath("$.header.statusCode").isEqualTo(ResponseCode.USER_NOT_FOUND.getStatusCode())
                .jsonPath("$.header.message").value(Matchers.containsString("User is not found. User id : " + userId));
    }

    @Test
    void updateUser_success() {
        var userId = "user_id";
        var updateUserRequest = UpdateUserRequest.builder()
                .nickname("change_nickname")
                .password("change_password")
                .phoneNumber("change_phoneNumber")
                .autoLogin(false)
                .build();
        var updateUserCommand = UpdateUserCommand.mapRequestToCommand(updateUserRequest);
        var user = UserUtil.createUser(updateUserCommand);

        when(updateUserUseCase.updateUser(userId, updateUserCommand)).thenReturn(Mono.just(user));

        webTestClient.put()
                .uri("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateUserRequest), UpdateUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(true)
                .jsonPath("$.body.userId").isEqualTo(user.userId())
                .jsonPath("$.body.nickname").isEqualTo(updateUserRequest.nickname());
    }

    @Test
    void updateUser_failed_userNotFoundException() {
        var userId = "user_id";
        var updateUserRequest = UpdateUserRequest.builder()
                .nickname("change_nickname")
                .password("change_password")
                .phoneNumber("change_phoneNumber")
                .autoLogin(false)
                .build();
        var updateUserCommand = UpdateUserCommand.mapRequestToCommand(updateUserRequest);

        when(updateUserUseCase.updateUser(userId, updateUserCommand)).thenReturn(Mono.error(new UserNotFoundException("User is not found. User id : " + userId)));

        webTestClient.put()
                .uri("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateUserRequest), UpdateUserRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(false)
                .jsonPath("$.header.statusCode").isEqualTo(ResponseCode.USER_NOT_FOUND.getStatusCode())
                .jsonPath("$.header.message").value(Matchers.containsString("User is not found. User id : " + userId));
    }

    @Test
    void deleteUser_success() {
        var userId = "user_id";

        when(deleteUserUseCase.deleteUser(userId)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/users/" + userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(true)
                .jsonPath("$.body").isEqualTo(true);
    }
}