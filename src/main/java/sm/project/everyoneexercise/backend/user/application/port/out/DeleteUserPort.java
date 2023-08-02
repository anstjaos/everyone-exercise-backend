package sm.project.everyoneexercise.backend.user.application.port.out;

import reactor.core.publisher.Mono;

public interface DeleteUserPort {
    Mono<Void> deleteUser(String userId);
}
