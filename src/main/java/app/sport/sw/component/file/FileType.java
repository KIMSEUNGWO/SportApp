package app.sport.sw.component.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {

    PROFILE("profile","/original/profile/", "/thumbnail/profile/"),
    CLUB_IMAGE("club", "/original/club/", "/thumbnail/club/"),;

    private final String dir;
    private final String originalPath;
    private final String thumbnailPath;

    public static FileType findDir(String fileType) {
        FileType[] values = FileType.values();
        for (FileType value : values) {
            if (value.dir.equals(fileType)) return value;
        }
        return null;
    }
}
