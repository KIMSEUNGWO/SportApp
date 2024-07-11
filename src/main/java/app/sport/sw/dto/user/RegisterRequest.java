package app.sport.sw.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {

    private String nickname;
    private String intro;
    private String sex;
    private LocalDate birth;
}
