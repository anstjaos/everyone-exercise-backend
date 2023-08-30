package sm.project.everyoneexercise.backend.place.adapter.out.persistence;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "place")
@Getter
public class PlaceEntity {
    @Id
    private String placeId;
    private String name;
    private String phoneNumber;
    private String url;
    private List<String> exerciseId;
    private String geoLocation;

    public PlaceEntity() {}
    @Builder
    public PlaceEntity(String placeId,
                       String name,
                       String phoneNumber,
                       String url,
                       List<String> exerciseId,
                       String geoLocation) {
        this.placeId = placeId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.exerciseId = exerciseId;
        this.geoLocation = geoLocation;
    }
}
