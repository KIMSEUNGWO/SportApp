package app.sport.sw.component.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final FilePathHelper filePathHelper;

    @GetMapping(value = "/images/original/{fileType}/{filename}", produces = {"image/png", "image/jpg", "image/jpeg"})
    public Resource originalImage(@PathVariable(name = "fileType") String fileType, @PathVariable(name = "filename") String filename) throws MalformedURLException {
        FileType type = FileType.findDir(fileType);
        return new UrlResource("file:" + filePathHelper.getOriginalPath(filename, type));
    }

    @GetMapping(value = "/images/thumbnail/{fileType}/{filename}", produces = {"image/png", "image/jpg", "image/jpeg"})
    public Resource thumbnailImage(@PathVariable(name = "fileType") String fileType, @PathVariable(name = "filename") String filename) throws MalformedURLException {
        FileType type = FileType.findDir(fileType);
        return new UrlResource("file:" + filePathHelper.getThumbnailPath(filename, type));
    }
}
