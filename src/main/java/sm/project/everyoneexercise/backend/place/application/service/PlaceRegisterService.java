package sm.project.everyoneexercise.backend.place.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceUseCase;
import sm.project.everyoneexercise.backend.place.application.port.out.RegisterPlacePort;
import sm.project.everyoneexercise.backend.place.domain.Place;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PlaceRegisterService implements RegisterPlaceUseCase {
    private final RegisterPlacePort registerPlacePort;

    @Override
    public Mono<Place> registerPlace(RegisterPlaceCommand registerPlaceCommand) {
        var placeId = UUID.randomUUID().toString();
        return registerPlacePort.registerPlace(placeId, registerPlaceCommand);
    }
}
