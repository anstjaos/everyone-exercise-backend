package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface UserRepository extends ReactiveCrudRepository<UserJpaEntity, String> {
}
