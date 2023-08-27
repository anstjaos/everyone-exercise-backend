package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.application.port.out.RegisterPlacePort;
import sm.project.everyoneexercise.backend.place.domain.Place;

@AllArgsConstructor
@Component
class PlacePersistenceAdapter implements RegisterPlacePort {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Override
    @Transactional
    public Mono<Place> registerPlace(String placeId, RegisterPlaceCommand registerPlaceCommand) {
        var placeEntity = placeMapper.mapCommandToEntity(placeId, registerPlaceCommand);
        return placeRepository.save(placeEntity)
                .map(placeMapper::mapEntityToDomainEntity);
    }
}
