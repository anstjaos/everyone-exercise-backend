package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.user.application.port.out.DeleteUserPort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDeleteServiceTest {
    @Mock
    private DeleteUserPort deleteUserPort;

    @InjectMocks
    private UserDeleteService userDeleteService;

    @Test
    void deleteUser_success() {
        var userId = "user_id";

        when(deleteUserPort.deleteUser(userId)).thenReturn(Mono.empty());

        StepVerifier.create(userDeleteService.deleteUser(userId))
                .verifyComplete();
    }
}