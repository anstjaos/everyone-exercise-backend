package sm.project.everyoneexercise.backend.place.application.port.out;

import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.domain.Place;

public interface RegisterPlacePort {
    Mono<Place> registerPlace(RegisterPlaceCommand registerPlaceCommand);
}
