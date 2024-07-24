package app.sport.sw.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseTotalCount {

    private final int totalCount;
    private final List<ResponseComment> comments;
}
