package app.sport.sw.response;

public enum ClubError implements ResponseCode {

    NOT_EXISTS,
    NOT_JOIN_USER
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
