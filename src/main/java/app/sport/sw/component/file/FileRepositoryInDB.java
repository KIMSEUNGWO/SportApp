package app.sport.sw.component.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FileRepositoryInDB implements FileRepository {

    private final FilePathHelper filePathHelper;


    @Override
    public void upload(MultipartFile file, FileType fileType, String storeName) throws IOException {

        String originalPath = filePathHelper.getOriginalPath(storeName, fileType);
        String thumbnailPath = filePathHelper.getThumbnailPath(storeName, fileType);

        System.out.println("originalPath = " + originalPath);
        System.out.println("thumbnailPath = " + thumbnailPath);

        File data = new File(originalPath);
        file.transferTo(data);

        Thumbnails.of(data)
            .size(100, 100)
            .toFile(thumbnailPath);

    }

    @Override
    public void delete(String storeName, FileType fileType) {
        if (storeName == null) return;

        String originalPath = filePathHelper.getOriginalPath(storeName, fileType);
        String thumbnailPath = filePathHelper.getThumbnailPath(storeName, fileType);

        boolean originalImageDelete = new File(originalPath).delete();
        boolean thumbnailImageDelete = new File(thumbnailPath).delete();

        if (!originalImageDelete && !thumbnailImageDelete) {
            log.error("파일 삭제 실패 !! \noriginalImage : {} \nthumbnailImage : {}", originalPath, thumbnailPath);
        }
    }
}
