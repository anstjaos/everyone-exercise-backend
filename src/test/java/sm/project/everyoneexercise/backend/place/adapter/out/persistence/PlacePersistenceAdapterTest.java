package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.place.PlaceUtil;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DirtiesContext
class PlacePersistenceAdapterTest {
    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private PlaceMapper placeMapper;

    @InjectMocks
    private PlacePersistenceAdapter placePersistenceAdapter;

    @Test
    void registerPlace_success() {
        var registerPlaceCommand = PlaceUtil.createRegisterPlaceCommand();
        var placeId = "place-id";
        var placeEntity = PlaceUtil.createPlaceEntity(registerPlaceCommand);
        var place = PlaceUtil.createPlace(registerPlaceCommand);

        when(placeMapper.mapCommandToEntity(registerPlaceCommand)).thenReturn(placeEntity);
        when(placeRepository.save(placeEntity)).thenReturn(Mono.just(placeEntity));
        when(placeMapper.mapEntityToDomainEntity(placeEntity)).thenReturn(place);

        var registerPlace = placePersistenceAdapter.registerPlace(registerPlaceCommand);

        StepVerifier.create(registerPlace)
                .expectNextMatches(resultPlace -> resultPlace.name().equals(registerPlaceCommand.name())
                        && resultPlace.phoneNumber().equals(registerPlaceCommand.phoneNumber())
                        && resultPlace.url().equals(registerPlaceCommand.url())
                        && resultPlace.exerciseId().equals(registerPlaceCommand.exerciseId())
                        && resultPlace.geoLocation().equals(registerPlaceCommand.geoLocation()))
                .verifyComplete();
    }
}