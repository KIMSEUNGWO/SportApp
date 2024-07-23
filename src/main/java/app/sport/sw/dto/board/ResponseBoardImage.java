package app.sport.sw.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseBoardImage {

    private final long imageId;
    private final String attachedImage;
}
