package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PlaceRepository extends ReactiveMongoRepository<PlaceEntity, String> {
}
