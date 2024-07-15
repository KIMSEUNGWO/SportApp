package app.sport.sw.exception.board;

import app.sport.sw.response.BoardError;
import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private final BoardError boardError;

    public BoardException(BoardError boardError) {
        this.boardError = boardError;
    }
}
