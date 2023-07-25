package sm.project.everyoneexercise.backend.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    VALIDATE_FAILED(400, "Validate failed."),
    USER_NOT_FOUND(400, "User is not found");

    final Integer statusCode;
    final String message;

    ResponseCode(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
