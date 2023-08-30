package sm.project.everyoneexercise.backend.place.adapter.in.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import sm.project.everyoneexercise.backend.common.ResponseCode;
import sm.project.everyoneexercise.backend.place.PlaceUtil;
import sm.project.everyoneexercise.backend.place.adapter.in.RegisterPlaceRequest;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceCommand;
import sm.project.everyoneexercise.backend.place.application.port.in.RegisterPlaceUseCase;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PlaceController.class)
class PlaceControllerTest {

    @MockBean
    private RegisterPlaceUseCase registerPlaceUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void registerPlace_failure_validateFailed() {
        var registerPlaceRequest = RegisterPlaceRequest.builder().build();

        webTestClient.post()
                .uri("/places")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(registerPlaceRequest), RegisterPlaceRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(false)
                .jsonPath("$.header.statusCode").isEqualTo(ResponseCode.VALIDATE_FAILED.getStatusCode())
                .jsonPath("$.header.message").value(Matchers.containsString("Place name is mandatory."));
    }

    @Test
    void registerPlace_success() {
        var registerPlaceRequest = RegisterPlaceRequest.builder()
                .name("name")
                .phoneNumber("phone-number")
                .url("url")
                .geoLocation("geo-location")
                .build();

        var registerPlaceCommand = RegisterPlaceCommand.mapRequestToCommand(registerPlaceRequest);

        when(registerPlaceUseCase.registerPlace(registerPlaceCommand)).thenReturn(Mono.just(PlaceUtil.createPlace(registerPlaceCommand)));

        webTestClient.post()
                .uri("/places")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(registerPlaceRequest), RegisterPlaceRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.header.isSuccessful").isEqualTo(true)
                .jsonPath("$.header.statusCode").isEqualTo(200);
    }
}