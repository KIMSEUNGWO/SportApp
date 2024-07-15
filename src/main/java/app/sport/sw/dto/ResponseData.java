package app.sport.sw.dto;

import app.sport.sw.response.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> extends Response {

    private T data;

    public ResponseData(ResponseCode responseCode, T data) {
        super(responseCode);
        this.data = data;
    }
}
