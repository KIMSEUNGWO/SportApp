package app.sport.sw.exception;

import app.sport.sw.response.CommentError;
import lombok.Getter;

@Getter
public class CommentException extends CustomRuntimeException {

    public CommentException(CommentError commentError) {
        super(commentError);
    }
}
