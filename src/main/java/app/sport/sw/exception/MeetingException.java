package app.sport.sw.exception;

import app.sport.sw.response.MeetingError;

public class MeetingException extends CustomRuntimeException {

    public MeetingException(MeetingError meetingError) {
        super(meetingError);
    }
}
