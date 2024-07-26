package app.sport.sw.dto.board;

import app.sport.sw.enums.group.BoardType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class RequestBoardEdit {

    private List<MultipartFile> image;
    private String title;
    private String content;
    private BoardType boardType;
    private List<Long> removeImages;

    public void setRemoveImages(String removeImages) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.removeImages = objectMapper.readValue(removeImages, new TypeReference<List<Long>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
