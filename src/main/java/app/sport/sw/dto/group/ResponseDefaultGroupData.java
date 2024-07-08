package app.sport.sw.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseDefaultGroupData {

    final DefaultGroupInfo defaultInfo;
    final BoardGroupInfo boardInfo;

}
