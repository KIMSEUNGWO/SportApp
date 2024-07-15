package app.sport.sw.response;

public enum SuccessCode implements ResponseCode {

    OK;

    @Override
    public String getResult() {
        return this.name();
    }
}
