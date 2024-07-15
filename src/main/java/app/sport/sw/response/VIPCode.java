package app.sport.sw.response;

public enum VIPCode implements ResponseCode {

    NOT_VIP_USER,
    NOT_VIP_CLUB
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
