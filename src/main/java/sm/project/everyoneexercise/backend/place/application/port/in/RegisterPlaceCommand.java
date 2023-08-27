package sm.project.everyoneexercise.backend.place.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterPlaceCommand(@NotNull String placeId, @NotNull String name, @NotNull String phoneNumber,
                                   String url, List<String> exerciseId, String geoLocation) {
}
