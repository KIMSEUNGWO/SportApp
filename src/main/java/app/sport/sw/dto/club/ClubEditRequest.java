package app.sport.sw.dto.club;

import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@NoArgsConstructor
@Setter
public class ClubEditRequest {

    @Nullable
    private MultipartFile image;

    @Nullable
    @Length(min = 3, max = 20)
    private String title;
    @Nullable
    @Length(max = 300)
    private String intro;

    @Nullable
    private SportType sportType;

    @Nullable
    private Region region;

    @Nullable
    private Integer limitPerson;


}
