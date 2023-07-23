package sm.project.everyoneexercise.backend.user.adapter.in.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sm.project.everyoneexercise.backend.common.Response;
import sm.project.everyoneexercise.backend.common.ResponseCode;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.ReadUserUseCase;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserUseCase;
import sm.project.everyoneexercise.backend.user.domain.User;

import java.util.stream.Collectors;

@RestController()
@RequestMapping(path = "users")
class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final ReadUserUseCase readUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          ReadUserUseCase readUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.readUserUseCase = readUserUseCase;
    }

    @PostMapping()
    Response<?> registerUser(@Validated @RequestBody RegisterUserRequest registerUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return Response.fail(ResponseCode.VALIDATE_FAILED.getStatusCode(), errors);
        }
        registerUserUseCase.registerUser(RegisterUserCommand.mapRequestToCommand(registerUserRequest));
        return Response.success(true);
    }

    @GetMapping(path = "/{userId}")
    Response<User> readUser(@PathVariable String userId) {
        var user = readUserUseCase.readUser(userId);
        return Response.success(user);
    }
}
