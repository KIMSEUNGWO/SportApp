package app.sport.sw.dto;

import app.sport.sw.response.ResponseCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import static app.sport.sw.response.SuccessCode.*;

@Getter
public class Response {

    private final String result;

    public Response(ResponseCode responseCode) {
        this.result = responseCode.getResult();
    }

    public static ResponseEntity<Response> ok() {
        return ResponseEntity.ok(new Response(OK));
    }
    public static ResponseEntity<Response> ok(ResponseCode responseCode) {
        return ResponseEntity.ok(new Response(responseCode));
    }
    public static <T> ResponseEntity<Response> ok(T data) {
        return ResponseEntity.ok(new ResponseData<>(OK, data));
    }
}
