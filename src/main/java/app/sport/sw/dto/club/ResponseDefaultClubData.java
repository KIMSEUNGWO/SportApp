package app.sport.sw.dto.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseDefaultClubData {

    final DefaultClubInfo defaultInfo;
    final BoardClubInfo boardInfo;

}
