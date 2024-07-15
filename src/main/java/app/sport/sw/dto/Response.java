package app.sport.sw.dto;

import app.sport.sw.response.ResponseCode;
import lombok.Getter;

@Getter
public class Response {

    private final String result;

    public Response(ResponseCode responseCode) {
        this.result = responseCode.getResult();
    }
}
