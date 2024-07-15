package app.sport.sw.component.file;

import app.sport.sw.domain.BaseEntityImage;
import app.sport.sw.response.FileCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    public void editImage(MultipartFile file, BaseEntityImage imageEntity, FileType fileType) {

        if (file == null || file.getContentType() == null) return;

        String originalName = file.getOriginalFilename();
        String storeName = createStoreName(originalName);

        try {
            fileRepository.delete(imageEntity.getStoreName(), fileType);
            fileRepository.upload(file, fileType, storeName);
        } catch (IOException e) {
            throw new FileUploadException(FileCode.FAILED_TO_UPLOAD_FILE);
        }

        imageEntity.setImage(originalName, storeName, storeName);
    }

    private String createStoreName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();

        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos);

        return uuid + ext;
    }
}
