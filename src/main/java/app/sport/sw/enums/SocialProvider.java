package app.sport.sw.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SocialProvider {

    LINE;

    @JsonCreator
    public static SocialProvider from(String value) {
        return SocialProvider.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
