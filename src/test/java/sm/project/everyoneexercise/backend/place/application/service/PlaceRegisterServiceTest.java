package sm.project.everyoneexercise.backend.place.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.place.PlaceUtil;
import sm.project.everyoneexercise.backend.place.application.port.out.RegisterPlacePort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceRegisterServiceTest {
    @Mock
    private RegisterPlacePort registerPlacePort;

    @InjectMocks
    private PlaceRegisterService placeRegisterService;

    @Test
    void registerUser_success() {
        var registerPlaceCommand = PlaceUtil.createRegisterPlaceCommand();
        var placeId = "place-id";
        var place = PlaceUtil.createPlace(placeId, registerPlaceCommand);

        when(registerPlacePort.registerPlace(any(), any())).thenReturn(Mono.just(place));

        var registerPlace = placeRegisterService.registerPlace(registerPlaceCommand);

        StepVerifier.create(registerPlace)
                .expectNextMatches(registeredPlace -> registeredPlace.name().equals(registerPlaceCommand.name())
                        && registeredPlace.phoneNumber().equals(registerPlaceCommand.phoneNumber())
                        && registeredPlace.url().equals(registerPlaceCommand.url())
                        && registeredPlace.geoLocation().equals(registerPlaceCommand.geoLocation()))
                .verifyComplete();
    }
}