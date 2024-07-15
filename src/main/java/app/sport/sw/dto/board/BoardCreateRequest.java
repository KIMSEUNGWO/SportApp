package app.sport.sw.dto.board;

import app.sport.sw.enums.group.BoardType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class BoardCreateRequest {

    private List<MultipartFile> images;

    @NotNull
    @Length(min = 2, max = 20)
    private String title;

    @Nullable
    @Length(max = 300)
    private String content;

    private BoardType boardType;

}
