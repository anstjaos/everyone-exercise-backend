package sm.project.everyoneexercise.backend.common;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer statusCode, String errorMessage) {
    public static ErrorResponse of(ResponseCode responseCode, String errorMessage) {
        return ErrorResponse.builder()
                .statusCode(responseCode.statusCode)
                .errorMessage(errorMessage)
                .build();
    }
}
