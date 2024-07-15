package app.sport.sw.response;

public enum TokenError implements ResponseCode {

    TOKEN_EXPIRED,

    ACCESS_TOKEN_REQUIRE,

    REFRESH_TOKEN_EXPIRED;

    @Override
    public String getResult() {
        return this.name();
    }
}
