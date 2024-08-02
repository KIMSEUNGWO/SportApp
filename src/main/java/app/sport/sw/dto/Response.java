package app.sport.sw.dto;

import app.sport.sw.response.ResponseCode;
import app.sport.sw.response.SuccessCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import static app.sport.sw.response.SuccessCode.*;

@Getter
public class Response {

    private final String result;

    public Response(ResponseCode responseCode) {
        this.result = responseCode.getResult();
    }

    private static final Response defaultResponse = new Response(SuccessCode.OK);

    public static ResponseEntity<Response> ok() {
        return ResponseEntity.ok(defaultResponse);
    }
    public static ResponseEntity<Response> ok(ResponseCode responseCode) {
        return ResponseEntity.ok(new Response(responseCode));
    }
    public static <T> ResponseEntity<Response> ok(T data) {
        return ResponseEntity.ok(new ResponseData<>(OK, data));
    }
}
