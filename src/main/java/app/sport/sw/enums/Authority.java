package app.sport.sw.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    OWNER(1),
    MANAGER(2),
    USER(3);

    private final int orderBy;
}
