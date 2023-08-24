package sm.project.everyoneexercise.backend.place.application.port.in;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.place.domain.Place;

public interface RegisterPlaceUseCase {
    Mono<Place> registerPlace(RegisterPlaceCommand registerPlaceCommand);
}
