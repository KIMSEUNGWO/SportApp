package app.sport.sw.component.file;

import app.sport.sw.domain.BaseEntityImage;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.group.board.BoardImage;
import app.sport.sw.repository.BoardImageRepository;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.response.FileCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;


    public void editImage(MultipartFile file, BaseEntityImage imageEntity, FileType fileType) {

        if (file == null || file.getContentType() == null) return;

        String originalName = file.getOriginalFilename();
        String storeName = createName(originalName);
        String thumbnailName = createName(originalName);

        try {
            fileRepository.delete(imageEntity.getStoreName(), imageEntity.getThumbnailName(), fileType);
            fileRepository.upload(file, fileType, storeName, thumbnailName);
        } catch (IOException e) {
            throw new FileUploadException(FileCode.FAILED_TO_UPLOAD_FILE);
        }

        imageEntity.setImage(originalName, storeName, thumbnailName);
    }

    public void saveBoardImages(List<MultipartFile> files, Board board) {
        if (files == null || files.isEmpty()) return;

        List<BoardImage> saveImages = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file == null || file.getContentType() == null) continue;

            String originalName = file.getOriginalFilename();
            String storeName = createName(originalName);
            String thumbnailName = createName(originalName);

            try {
                fileRepository.upload(file, FileType.BOARD_IMAGE, storeName, thumbnailName);
            } catch (IOException e) {
                throw new FileUploadException(FileCode.FAILED_TO_UPLOAD_FILE);
            }

            BoardImage saveBoardImage = BoardImage.builder().board(board).build();
            saveBoardImage.setImage(originalName, storeName, thumbnailName);
            saveImages.add(saveBoardImage);
        }

        boardImageRepository.saveAll(saveImages);
    }

    public void editBoardImages(List<MultipartFile> files, Board board, List<Long> removeImages) {
        List<BoardImage> removeBoardImages = boardImageRepository.findAllById(removeImages);

        for (BoardImage removeBoardImage : removeBoardImages) {
            fileRepository.delete(removeBoardImage.getStoreName(), removeBoardImage.getThumbnailName(), FileType.BOARD_IMAGE);
        }
        boardImageRepository.deleteAllById(removeImages);


        saveBoardImages(files, board);
    }

    private String createName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();

        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos);

        return uuid + ext;
    }
}
