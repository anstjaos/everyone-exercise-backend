package sm.project.everyoneexercise.backend.place;

import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.domain.Place;

public class PlaceUtil {
    public static RegisterPlaceCommand createRegisterPlaceCommand() {
        return RegisterPlaceCommand.builder()
                .placeId("place-id")
                .name("name")
                .phoneNumber("phone-number")
                .url("url")
                .geoLocation("geo-location")
                .build();
    }

    public static Place createPlace(RegisterPlaceCommand registerPlaceCommand) {
        return Place.builder()
                .placeId(registerPlaceCommand.placeId())
                .name(registerPlaceCommand.name())
                .phoneNumber(registerPlaceCommand.phoneNumber())
                .url(registerPlaceCommand.url())
                .geoLocation(registerPlaceCommand.geoLocation())
                .build();
    }
}