package sm.project.everyoneexercise.backend.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sm.project.everyoneexercise.backend.exception.RegisterUserValidationException;
import sm.project.everyoneexercise.backend.exception.UserNotFoundException;

import static sm.project.everyoneexercise.backend.common.ResponseCode.USER_NOT_FOUND;
import static sm.project.everyoneexercise.backend.common.ResponseCode.VALIDATE_FAILED;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RegisterUserValidationException.class)
    @ResponseBody
    private Response<String> handleRegisterUserValidationException(RegisterUserValidationException e) {
        log.error("============ Register user exception error!", e);
        return Response.fail(VALIDATE_FAILED.getStatusCode(), e.getErrorMessage(), "Register user request is not valid.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    private Response<String> handleUserNotFoundException(UserNotFoundException e) {
        log.error("============ User not found exception error!", e);
        return Response.fail(USER_NOT_FOUND.getStatusCode(), e.getErrorMessage(), "User is not found.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Response<Exception> handleException(Exception e) {
        log.error("============ Exception error!", e);
        return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
    }
}
