package app.sport.sw.enums.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {

    NOTICE("notice"),
    FIRST_COMMENT("firstComment"),
    OPEN_BOARD("openBoard");

    private final String lang;

}
