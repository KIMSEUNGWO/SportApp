package app.sport.sw.domain.user;

import app.sport.sw.enums.SocialProvider;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class UserSocial {

    private String socialId;
    @Enumerated(EnumType.STRING)
    private SocialProvider provider;
    @Setter
    private String accessToken;

}
