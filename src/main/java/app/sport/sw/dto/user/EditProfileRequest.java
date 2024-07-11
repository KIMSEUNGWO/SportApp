package app.sport.sw.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileRequest {

    private MultipartFile image;
    private String nickName;
    private String intro;
}
