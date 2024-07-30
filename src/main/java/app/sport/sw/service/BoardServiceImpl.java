package app.sport.sw.service;

import app.sport.sw.component.file.FileService;
import app.sport.sw.domain.BaseEntityImage;
import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.board.RequestBoardEdit;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.board.ResponseBoardDetail;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.exception.ClubException;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.wrappers.BoardWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    private final BoardWrapper boardWrapper;

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

    @Transactional(readOnly = true)
    @Override
    public List<ResponseBoard> getBoardList(long clubId, BoardType boardType, Pageable pageable) {
        return boardRepository.findAllByClubIdAndBoardType(clubId, boardType, pageable)
            .stream()
            .map(boardWrapper::boardWrap)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseBoardDetail getBoardDetail(long boardId) {
        Board board = boardRepository.findById(boardId);
        return boardWrapper.boardDetailWrap(board);
    }

    @Override
    public void editBoard(long boardId, RequestBoardEdit boardEdit) {
        Board board = boardRepository.findById(boardId);
        board.edit(boardEdit);
        fileService.editBoardImages(boardEdit.getImage(), board, boardEdit.getRemoveImages());
    }

    @Override
    public List<? extends BaseEntityImage> deleteBoard(long boardId) {
        Board board = boardRepository.findById(boardId);
        boardRepository.delete(board);
        return board.getBoardImages();
    }
}
