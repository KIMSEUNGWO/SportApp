package app.sport.sw.response;

public enum BoardError implements ResponseCode {

    BOARD_NOT_EXISTS,
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
