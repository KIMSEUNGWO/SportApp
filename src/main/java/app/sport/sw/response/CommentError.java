package app.sport.sw.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentError implements ResponseCode {

    NOT_EXISTS_COMMENT,
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
