package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
}
