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
}
