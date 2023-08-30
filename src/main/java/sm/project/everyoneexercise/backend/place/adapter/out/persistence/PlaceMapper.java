package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import org.springframework.stereotype.Component;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.domain.Place;

@Component
class PlaceMapper {

    PlaceEntity mapCommandToEntity(RegisterPlaceCommand registerPlaceCommand) {
        return PlaceEntity.builder()
                .name(registerPlaceCommand.name())
                .phoneNumber(registerPlaceCommand.phoneNumber())
                .url(registerPlaceCommand.url())
                .exerciseId(registerPlaceCommand.exerciseId())
                .geoLocation(registerPlaceCommand.geoLocation())
                .build();
    }

    Place mapEntityToDomainEntity(PlaceEntity placeEntity) {
        return Place.builder()
                .placeId(placeEntity.getPlaceId())
                .name(placeEntity.getName())
                .phoneNumber(placeEntity.getPhoneNumber())
                .url(placeEntity.getUrl())
                .exerciseId(placeEntity.getExerciseId())
                .geoLocation(placeEntity.getGeoLocation())
                .build();
    }
}
