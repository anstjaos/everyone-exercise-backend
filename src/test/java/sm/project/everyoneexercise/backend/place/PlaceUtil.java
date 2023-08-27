package sm.project.everyoneexercise.backend.place;

import sm.project.everyoneexercise.backend.place.adapter.out.persistence.PlaceEntity;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.domain.Place;

import java.util.List;

public class PlaceUtil {
    public static RegisterPlaceCommand createRegisterPlaceCommand() {
        return RegisterPlaceCommand.builder()
                .name("name")
                .phoneNumber("phone-number")
                .url("url")
                .exerciseId(List.of("exercise-id"))
                .geoLocation("geo-location")
                .build();
    }

    public static Place createPlace(String placeId, RegisterPlaceCommand registerPlaceCommand) {
        return Place.builder()
                .placeId(placeId)
                .name(registerPlaceCommand.name())
                .phoneNumber(registerPlaceCommand.phoneNumber())
                .url(registerPlaceCommand.url())
                .exerciseId(registerPlaceCommand.exerciseId())
                .geoLocation(registerPlaceCommand.geoLocation())
                .build();
    }

    public static PlaceEntity createPlaceEntity() {
        return PlaceEntity.builder()
                .placeId("place-id")
                .name("name")
                .phoneNumber("phone-number")
                .url("url")
                .exerciseId(List.of("exercise-id"))
                .geoLocation("geo-location")
                .build();
    }

    public static PlaceEntity createPlaceEntity(String placeId, RegisterPlaceCommand registerPlaceCommand) {
        return PlaceEntity.builder()
                .placeId(placeId)
                .name(registerPlaceCommand.name())
                .phoneNumber(registerPlaceCommand.phoneNumber())
                .url(registerPlaceCommand.url())
                .exerciseId(registerPlaceCommand.exerciseId())
                .geoLocation(registerPlaceCommand.geoLocation())
                .build();
    }
}
