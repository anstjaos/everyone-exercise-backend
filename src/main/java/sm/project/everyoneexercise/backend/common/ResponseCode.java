package sm.project.everyoneexercise.backend.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    VALIDATE_FAILED(400, "Validate failed.");

    final Integer statusCode;
    final String message;

    ResponseCode(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
