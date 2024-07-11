package app.sport.sw.service;

import app.sport.sw.component.file.FileService;
import app.sport.sw.component.file.FileType;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.EditProfileRequest;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final JpaUserRepository jpaUserRepository;
    private final FileService fileService;

    public ResponseProfile getUserProfile(long userId) {
        return jpaUserRepository.findById(userId)
            .map(user -> ResponseProfile.builder()
                .image(user.getImage())
                .name(user.getNickName())
                .intro(user.getUserInfo().getIntro())
                .sex(user.getUserInfo().getSex())
                .birth(user.getUserInfo().getBirthDate())
                .groupCount(user.getUserClubList().size())
                .inviteCount(0)
                .likeCount(0)
                .build()
        ).orElse(new ResponseProfile());
    }

    public void editUserProfile(long id, EditProfileRequest editProfileRequest) {

        Optional<User> findUser = jpaUserRepository.findById(id);
        if (findUser.isEmpty()) return;

        User user = findUser.get();
        fileService.editImage(editProfileRequest.getImage(), user.getProfile(), FileType.PROFILE);

        String nickName = editProfileRequest.getNickName();
        String intro = editProfileRequest.getIntro();

        if (nickName != null && !nickName.isEmpty()) {
            user.setNickName(nickName);
        }

        if (intro != null && !intro.isEmpty()) {
            user.getUserInfo().setIntro(intro);
        }
    }

    public boolean distinctNickname(String nickname) {
        if (nickname == null || nickname.isEmpty()) return false;

        return jpaUserRepository.existsByNickName(nickname);
    }
}
