package app.sport.sw.component.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilePathHelperInDB implements FilePathHelper {

    @Value("${file.dir.server}")
    private String fileDir;


    @Override
    public String getOriginalPath(String fileName, FileType type) {
        return getPath(fileName, type.getOriginalPath());
    }

    @Override
    public String getThumbnailPath(String fileName, FileType type) {
        return getPath(fileName, type.getThumbnailPath());
    }

    private String getPath(String fileName, String folderName) {
        return String.format("%s%s%s", fileDir, folderName, fileName);
    }
}
