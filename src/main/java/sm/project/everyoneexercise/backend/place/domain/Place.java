package sm.project.everyoneexercise.backend.place.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record Place(String placeId, String name, String phoneNumber, String url, List<String> exerciseId, String geoLocation) {
}
