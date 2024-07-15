package app.sport.sw.response;

public enum UserCode implements ResponseCode {

    REGISTER;

    @Override
    public String getResult() {
        return this.name();
    }
}
