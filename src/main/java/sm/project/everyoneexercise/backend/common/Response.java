package sm.project.everyoneexercise.backend.common;

import lombok.Builder;

@Builder
public record Response<T>(ResponseHeader header, T body) {

    public static <T> Response<T> success(T body) {
        return Response.<T>builder()
                .header(ResponseHeader.ok())
                .body(body)
                .build();
    }

    public static <T> Response<T> fail(Integer statusCode, String message) {
        return Response.<T>builder()
                .header(ResponseHeader.fail(statusCode, message))
                .build();
    }

    public static <T> Response<T> fail(Integer statusCode, String message, T body) {
        return Response.<T>builder()
                .header(ResponseHeader.fail(statusCode, message))
                .body(body)
                .build();
    }
}
