package app.sport.sw.enums.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {

    NOTICE("notice"),
    FIRST_COMMENT("firstComment"),
    OPEN_BOARD("openBoard");

    private final String lang;

    @JsonCreator
    public static BoardType fromJson(String data) {
        for (BoardType boardType : BoardType.values()) {
            if (boardType.name().equals(data)) return boardType;
        }
        return null;
    }

}
