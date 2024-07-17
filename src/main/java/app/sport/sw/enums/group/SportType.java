package app.sport.sw.enums.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SportType {

    SOCCER("soccer"),
    BASEBALL("baseball"),
    TENNIS("tennis"),
    BADMINTON("badminton"),
    BASKETBALL("basketball"),
    RUNNING("running");

    private String lang;

    @JsonCreator
    public static SportType fromJson(String data) {
        for (SportType sportType : SportType.values()) {
            if (sportType.name().equals(data)) return sportType;
        }
        return null;
    }
}
