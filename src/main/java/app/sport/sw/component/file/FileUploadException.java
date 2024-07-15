package app.sport.sw.component.file;

import app.sport.sw.response.FileCode;
import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException {

    private final FileCode fileCode;

    public FileUploadException(FileCode fileCode) {
        this.fileCode = fileCode;
    }
}
