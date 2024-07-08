package app.sport.sw.enums.group;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SportType {

    SOCCER("soccer"),
    BASEBALL("baseball"),
    TENNIS("tennis"),
    BADMINTON("badminton"),
    BASKETBALL("basketball");

    private String lang;
}
