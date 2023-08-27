package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import sm.project.everyoneexercise.backend.place.PlaceUtil;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class PlaceRepositoryTest {
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    void savePlace_success() {
        var placeEntity = PlaceUtil.createPlaceEntity();

        var savePlace = placeRepository.save(placeEntity);

        StepVerifier.create(savePlace)
                .expectNextMatches(place -> place.getPlaceId().equals(placeEntity.getPlaceId())
                        && place.getName().equals(placeEntity.getName())
                        && place.getPhoneNumber().equals(placeEntity.getPhoneNumber())
                        && place.getUrl().equals(placeEntity.getUrl())
                        && place.getExerciseId().equals(placeEntity.getExerciseId())
                        && place.getGeoLocation().equals(placeEntity.getGeoLocation()))
                .verifyComplete();
    }

    @Test
    void findByIdPlace_success() {
        var placeEntity = PlaceUtil.createPlaceEntity();
        var saveAndFindPlace = placeRepository.save(placeEntity)
                .then(placeRepository.findById(placeEntity.getPlaceId()));

        StepVerifier.create(saveAndFindPlace)
                .expectNextMatches(place -> place.getPlaceId().equals(placeEntity.getPlaceId())
                        && place.getName().equals(placeEntity.getName())
                        && place.getPhoneNumber().equals(placeEntity.getPhoneNumber())
                        && place.getUrl().equals(placeEntity.getUrl())
                        && place.getExerciseId().equals(placeEntity.getExerciseId())
                        && place.getGeoLocation().equals(placeEntity.getGeoLocation()))
                .verifyComplete();
    }

    @Test
    void deleteByIdPlace_success() {
        var placeEntity = PlaceUtil.createPlaceEntity();
        var saveAndFindPlace = placeRepository.save(placeEntity)
                .then(placeRepository.findById(placeEntity.getPlaceId()));

        StepVerifier.create(saveAndFindPlace)
                .expectNextMatches(place -> place.getPlaceId().equals(placeEntity.getPlaceId()))
                .verifyComplete();

        var deleteAndFindPlace = placeRepository.deleteById(placeEntity.getPlaceId())
                .then(placeRepository.findById(placeEntity.getPlaceId()));

        StepVerifier.create(deleteAndFindPlace)
                .expectNextCount(0)
                .verifyComplete();
    }
}