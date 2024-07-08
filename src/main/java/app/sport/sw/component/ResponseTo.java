package app.sport.sw.component;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ResponseTo {

    private final RestTemplate restTemplate;

    public <T> T get(String uri, Class<T> clazz) {
        return restTemplate.getForObject(uri, clazz);
    }

    public <T> T getProfile(String uri, Class<T> clazz, HttpEntity<String> httpEntity) {
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, clazz).getBody();
    }
}
