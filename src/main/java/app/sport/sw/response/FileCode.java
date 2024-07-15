package app.sport.sw.response;

public enum FileCode implements ResponseCode {

    MAX_UPLOAD_SIZE_EXCEEDED,
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
