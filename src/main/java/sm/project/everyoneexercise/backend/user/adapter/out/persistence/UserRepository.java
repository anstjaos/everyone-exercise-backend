package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
}
