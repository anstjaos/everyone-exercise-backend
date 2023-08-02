package sm.project.everyoneexercise.backend.user.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.common.Response;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.adapter.in.UpdateUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.*;
import sm.project.everyoneexercise.backend.user.domain.User;

import static sm.project.everyoneexercise.backend.common.ResponseCode.VALIDATE_FAILED;

@RestController
@RequestMapping(path = "users")
class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final ReadUserUseCase readUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          ReadUserUseCase readUserUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          DeleteUserUseCase deleteUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.readUserUseCase = readUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping
    Mono<Response<Boolean>> registerUser(@Valid @RequestBody Mono<RegisterUserRequest> registerUserRequest) {
        return registerUserRequest
                .map(RegisterUserCommand::mapRequestToCommand)
                .flatMap(registerUserUseCase::registerUser)
                .map(user -> Response.success(true))
                .onErrorResume(WebExchangeBindException.class, ex ->
                    Mono.just(Response.fail(VALIDATE_FAILED.getStatusCode(), ex.getMessage()))
                );
    }

    @GetMapping(path = "/{userId}")
    Mono<Response<User>> readUser(@PathVariable String userId) {
        return readUserUseCase.readUser(userId)
                .map(Response::success);
    }

    @PutMapping(path = "/{userId}")
    Mono<Response<User>> updateUser(@PathVariable String userId, @RequestBody Mono<UpdateUserRequest> updateUserRequest) {
        return updateUserRequest
                .map(UpdateUserCommand::mapRequestToCommand)
                .flatMap(updateUserCommand -> updateUserUseCase.updateUser(userId, updateUserCommand))
                .map(Response::success);
    }

    @DeleteMapping(path = "/{userId}")
    Response<Boolean> deleteUser(@PathVariable String userId) {
        deleteUserUseCase.deleteUser(userId);
        return Response.success(true);
    }
}
