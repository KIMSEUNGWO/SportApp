package app.sport.sw.dto.club;

import app.sport.sw.enums.group.SportType;
import app.sport.sw.enums.region.Region;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
@NoArgsConstructor
public class ClubCreateRequest {

    @NotNull
    private SportType sportType;
    @NotNull
    private Region region;
    @Length(min = 3, max = 20)
    private String title;
    @Length(max = 300)
    private String intro;

    @Min(3)
    @Max(10)
    private int limitPerson = 10;
}
