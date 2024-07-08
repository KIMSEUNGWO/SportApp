package app.sport.sw.dto.user;

import app.sport.sw.enums.SocialProvider;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@ToString
public class SocialLoginDto {

    @NotEmpty
    private String socialId;
    @NotNull
    private SocialProvider provider;
    @NotNull
    private String accessToken;
}
