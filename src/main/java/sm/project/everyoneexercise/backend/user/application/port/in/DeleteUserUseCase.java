package sm.project.everyoneexercise.backend.user.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteUserUseCase {
    Mono<Void> deleteUser(String userId);
}
