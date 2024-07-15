package app.sport.sw.component.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    void upload(MultipartFile file, FileType fileType, String storeName, String thumbnailName) throws IOException;

    void delete(String storeName, String thumbnailName, FileType fileType);
}
