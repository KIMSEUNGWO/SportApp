package app.sport.sw.service;

import app.sport.sw.component.authority.AuthorityClubChecker;
import app.sport.sw.component.file.FileService;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.exception.ClubException;
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
    private final AuthorityClubChecker clubChecker;

    @Override
    public long createBoard(long clubId, CustomUserDetails userDetails, BoardCreateRequest createRequest) {

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

        List<MultipartFile> images = createRequest.getImage();
        if (images != null && !images.isEmpty()) {
//            if (!clubChecker.isPremium(userClub.getClub())) {
//                throw new VIPException(VIPCode.NOT_VIP_CLUB);
//            }
            fileService.saveBoardImages(images.subList(0, Math.min(4, images.size())), saveBoard);
        }

        return saveBoard.getId();
    }

    @Override
    public List<ResponseBoard> getBoardList(long clubId, BoardType boardType) {
        List<Board> boards = boardRepository.findAllByClubIdAndBoardType(clubId, boardType);
        return boards.stream().map(board ->
            ResponseBoard.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardType(board.getBoardType())
                .thumbnailBoard(board.getMainThumbnail())
                .likeCount(0) // 좋아요 기능 활성화시 변경
                .commentCount(board.getComments().size())
                .createDate(board.getCreateDate())
                .userId(board.getUser().getId())
                .thumbnailBoard(board.getUser().getThumbnail())
                .nickname(board.getUser().getNickName())
                .build()
            ).toList();
    }
}
