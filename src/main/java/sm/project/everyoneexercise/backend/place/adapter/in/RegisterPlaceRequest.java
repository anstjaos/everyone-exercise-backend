package sm.project.everyoneexercise.backend.place.adapter.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterPlaceRequest(@NotBlank(message = "Place name is mandatory.") String name,
                                   String phoneNumber,
                                   String url,
                                   String geoLocation) {
}
