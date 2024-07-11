package app.sport.sw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> extends Response {

    private T data;

    public ResponseData(String result, T data) {
        super(result);
        this.data = data;
    }
}
