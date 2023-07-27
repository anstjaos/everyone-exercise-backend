package sm.project.everyoneexercise.backend.user.adapter.in.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sm.project.everyoneexercise.backend.common.Response;
import sm.project.everyoneexercise.backend.exception.RegisterUserValidationException;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.adapter.in.UpdateUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.*;
import sm.project.everyoneexercise.backend.user.domain.User;

import java.util.stream.Collectors;

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
    Response<Boolean> registerUser(@Validated @RequestBody RegisterUserRequest registerUserRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            throw new RegisterUserValidationException(errors);
        }
        registerUserUseCase.registerUser(RegisterUserCommand.mapRequestToCommand(registerUserRequest));
        return Response.success(true);
    }

    @GetMapping(path = "/{userId}")
    Response<User> readUser(@PathVariable String userId) {
        var user = readUserUseCase.readUser(userId);
        return Response.success(user);
    }

    @PutMapping(path = "/{userId}")
    Response<User> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest updateUserRequest) {
        var user = updateUserUseCase.updateUser(userId, UpdateUserCommand.mapRequestToCommand(updateUserRequest));
        return Response.success(user);
    }

    @DeleteMapping(path = "/{userId}")
    Response<Boolean> deleteUser(@PathVariable String userId) {
        deleteUserUseCase.deleteUser(userId);
        return Response.success(true);
    }
}
