package app.sport.sw.response;

public enum FileCode implements ResponseCode {

    MAX_UPLOAD_SIZE_EXCEED,
    FAILED_TO_UPLOAD_FILE,
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
