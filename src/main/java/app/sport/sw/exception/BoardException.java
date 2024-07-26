package app.sport.sw.exception;

import app.sport.sw.response.BoardError;
import lombok.Getter;

@Getter
public class BoardException extends CustomRuntimeException {

    public BoardException(BoardError boardError) {
        super(boardError);
    }

}
