package sm.project.everyoneexercise.backend.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Response<Exception> handleException(Exception e) {
        log.error("============ Exception Error!", e);
        return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
    }
}
