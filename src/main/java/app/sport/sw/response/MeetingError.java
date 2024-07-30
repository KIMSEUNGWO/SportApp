package app.sport.sw.response;

public enum MeetingError implements ResponseCode {

    MEETING_NOT_EXISTS,
    MEETING_ALREADY_JOIN,
    MEETING_UNAUTHORIZED,
    MEETING_NOT_JOIN_USER
    ;

    @Override
    public String getResult() {
        return name();
    }
}
