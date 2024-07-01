package app.sport.sw.domain.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Embeddable
@Getter
public class MemberInfo {

    private String name;
    private LocalDateTime birth;
    private char sex;

}
