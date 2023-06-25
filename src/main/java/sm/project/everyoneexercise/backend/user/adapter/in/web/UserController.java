package sm.project.everyoneexercise.backend.user.adapter.in.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sm.project.everyoneexercise.backend.common.ErrorResponse;
import sm.project.everyoneexercise.backend.common.Response;
import sm.project.everyoneexercise.backend.common.ResponseCode;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserUseCase;

import java.util.stream.Collectors;

@RestController
class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping(path = "/users")
    Response<?> registerUser(@Validated @RequestBody RegisterUserRequest registerUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return Response.success(ErrorResponse.of(ResponseCode.VALIDATE_FAILED, errors));
        }
        registerUserUseCase.registerUser(RegisterUserCommand.mapRequestToCommand(registerUserRequest));
        return Response.success(true);
    }
}
