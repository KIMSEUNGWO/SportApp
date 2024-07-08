package app.sport.sw.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseProfile {

    private String image;
    private String name;
    private char sex;
    private LocalDate birth;
    private int groupCount;
    private int inviteCount;
    private int likeCount;
}
