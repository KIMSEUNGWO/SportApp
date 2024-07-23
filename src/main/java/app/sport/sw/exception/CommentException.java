package app.sport.sw.exception;

import app.sport.sw.response.CommentError;
import lombok.Getter;

@Getter
public class CommentException extends RuntimeException {

    private final CommentError commentError;

    public CommentException(CommentError commentError) {
        this.commentError = commentError;
    }
}
