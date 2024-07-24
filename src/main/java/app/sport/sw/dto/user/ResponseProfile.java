package app.sport.sw.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseProfile {

    private long id;
    private String image;
    private String name;
    private String intro;
    private char sex;
    private LocalDate birth;
    private int groupCount;
    private int inviteCount;
    private int likeCount;
}
