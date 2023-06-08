package sm.project.everyoneexercise.backend.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    VALIDATE_FAILED(400);

    final Integer statusCode;

    ResponseCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
