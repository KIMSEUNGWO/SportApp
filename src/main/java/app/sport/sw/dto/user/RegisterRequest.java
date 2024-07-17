package app.sport.sw.dto.user;

import app.sport.sw.enums.SocialProvider;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    @NotEmpty
    private String socialId;
    @NotNull
    private SocialProvider provider;
    @NotNull
    private String accessToken;
    @NotNull
    private String nickname;
    private String intro;
    @NotNull
    private String sex;
    @NotNull
    private LocalDate birth;
}
