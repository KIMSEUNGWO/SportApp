package app.sport.sw.domain.user;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserInfo {

    private char sex;
    @Setter
    private String intro;
    private LocalDate birthDate;
}
