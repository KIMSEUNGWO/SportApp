package app.sport.sw.response;

public enum MeetingError implements ResponseCode {

    MEETING_NOT_EXISTS,
    MEETING_ALREADY_JOIN;

    @Override
    public String getResult() {
        return name();
    }
}
