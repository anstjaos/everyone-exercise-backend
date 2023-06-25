package sm.project.everyoneexercise.backend.common;

import lombok.Builder;

@Builder
public record ResponseHeader(Boolean isSuccessful, Integer statusCode, String message) {

    public static ResponseHeader ok() {
        return ResponseHeader.builder()
                .isSuccessful(true)
                .statusCode(200)
                .build();
    }
}
