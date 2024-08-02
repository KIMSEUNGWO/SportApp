package app.sport.sw.domain.meeting;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String address;
    // 위도
    private Double latitude;
    // 경도
    private Double longitude;
}
