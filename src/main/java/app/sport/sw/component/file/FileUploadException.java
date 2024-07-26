package app.sport.sw.component.file;

import app.sport.sw.exception.CustomRuntimeException;
import app.sport.sw.response.FileCode;
import lombok.Getter;

@Getter
public class FileUploadException extends CustomRuntimeException {

    public FileUploadException(FileCode fileCode) {
        super(fileCode);
    }
}
