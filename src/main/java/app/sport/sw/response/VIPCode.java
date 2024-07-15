package app.sport.sw.response;

public enum VIPCode implements ResponseCode {

    NOT_VIP
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
