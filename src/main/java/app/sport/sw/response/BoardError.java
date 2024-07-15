package app.sport.sw.response;

public enum BoardError implements ResponseCode {

    BOARD_NOT_EXISTS,
    BOARD_NOT_OWNER
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
