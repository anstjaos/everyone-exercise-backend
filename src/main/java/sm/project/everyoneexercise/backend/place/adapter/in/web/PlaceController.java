package sm.project.everyoneexercise.backend.place.adapter.in.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.common.Response;
import sm.project.everyoneexercise.backend.place.adapter.in.RegisterPlaceRequest;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceUseCase;

import static sm.project.everyoneexercise.backend.common.ResponseCode.VALIDATE_FAILED;

@RestController
@RequestMapping(path = "places")
class PlaceController {
    private final RegisterPlaceUseCase registerPlaceUseCase;

    public PlaceController(RegisterPlaceUseCase registerPlaceUseCase) {
        this.registerPlaceUseCase = registerPlaceUseCase;
    }

    @PostMapping
    Mono<Response<Boolean>> registerPlace(@Valid @RequestBody Mono<RegisterPlaceRequest> registerPlaceRequest) {
        return registerPlaceRequest
                .map(RegisterPlaceCommand::mapRequestToCommand)
                .flatMap(registerPlaceUseCase::registerPlace)
                .map(place -> Response.success(true))
                .onErrorResume(WebExchangeBindException.class, ex ->
                        Mono.just(Response.fail(VALIDATE_FAILED.getStatusCode(), ex.getMessage()))
                );
    }
}
