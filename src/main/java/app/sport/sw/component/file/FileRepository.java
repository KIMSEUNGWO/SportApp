package app.sport.sw.component.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    void upload(MultipartFile file, FileType fileType, String storeName) throws IOException;

    void delete(String storeName, FileType fileType);
}
