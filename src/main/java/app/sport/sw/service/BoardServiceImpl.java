package app.sport.sw.service;

import app.sport.sw.component.file.FileService;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.exception.club.ClubException;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.response.ClubError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final UserClubRepository userClubRepository;
    private final BoardRepository boardRepository;
    private final FileService fileService;

    @Override
    public void createBoard(long clubId, CustomUserDetails userDetails, BoardCreateRequest createRequest) {

        UserClub userClub = userClubRepository
                .findByClubIdAndUserId(clubId, userDetails)
                .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER));

        BoardType boardType = createRequest.getBoardType();

        if (boardType == BoardType.NOTICE && userClub.getAuthority() != Authority.OWNER) {
            throw new ClubException(ClubError.CLUB_NOT_OWNER);
        }

        Board saveBoard = Board.builder()
                .club(userClub.getClub())
                .user(userClub.getUser())
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .boardType(boardType)
                .build();

        boardRepository.save(saveBoard);

        List<MultipartFile> images = createRequest.getImages();
        if (images != null && !images.isEmpty()) {
            fileService.saveBoardImages(images.subList(0, Math.min(4, images.size())), saveBoard);
        }

    }
}
