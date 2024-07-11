package app.sport.sw.component.file;

public interface FilePathHelper {

    String getOriginalPath(String fileName, FileType type);
    String getThumbnailPath(String fileName, FileType type);
}
