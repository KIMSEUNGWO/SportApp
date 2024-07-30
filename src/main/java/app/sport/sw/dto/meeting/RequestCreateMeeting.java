package app.sport.sw.dto.meeting;


import app.sport.sw.annotation.DateRange;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestCreateMeeting {

    @NotNull
    @Length(min = 3, max = 20)
    private String title;

    @Length(max = 100)
    private String description;

    @DateRange(month = 2)
    private LocalDateTime meetingDate;


}
