package sm.project.everyoneexercise.backend.place.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sm.project.everyoneexercise.backend.place.adapter.in.RegisterPlaceRequest;

import java.util.List;

@Builder
public record RegisterPlaceCommand(@NotNull String name, @NotNull String phoneNumber,
                                   String url, List<String> exerciseId, String geoLocation) {

    public static RegisterPlaceCommand mapRequestToCommand(RegisterPlaceRequest request) {
        return RegisterPlaceCommand.builder()
                .name(request.name())
                .phoneNumber(request.phoneNumber())
                .url(request.url())
                .exerciseId(List.of())
                .geoLocation(request.geoLocation())
                .build();
    }
}
