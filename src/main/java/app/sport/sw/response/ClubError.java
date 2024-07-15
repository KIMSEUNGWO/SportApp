package app.sport.sw.response;

public enum ClubError implements ResponseCode {

    CLUB_NOT_EXISTS,
    CLUB_NOT_JOIN_USER,
    CLUB_NOT_OWNER,

    EXCEED_MAX_CLUB,
    INVALID_DATA
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
