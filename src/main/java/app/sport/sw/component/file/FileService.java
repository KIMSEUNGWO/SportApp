package app.sport.sw.component.file;

import app.sport.sw.domain.BaseEntityImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    public void editImage(MultipartFile file, BaseEntityImage imageEntity, FileType fileType) {

        System.out.println("editImage 시작 ");
        if (file == null || isNotImage(file)) return;

        String originalName = file.getOriginalFilename();
        String storeName = createStoreName(originalName);

        try {
            System.out.println("삭제되는 이미지 파일 이름 " + imageEntity.getStoreName());
            System.out.println("업로드되는 이미지 파일 이름 " + storeName);
            fileRepository.delete(imageEntity.getStoreName(), fileType);
            fileRepository.upload(file, fileType, storeName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException();
        }

        imageEntity.setImage(originalName, storeName, storeName);
    }

    private boolean isNotImage(MultipartFile file) {
        return file.getContentType() == null;
    }

    private String createStoreName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();

        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos);

        return uuid + ext;
    }
}
