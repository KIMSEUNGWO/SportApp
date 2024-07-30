package app.sport.sw.service;

import app.sport.sw.component.file.FileService;
import app.sport.sw.component.file.FileType;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.meeting.Meeting;
import app.sport.sw.domain.meeting.MeetingImage;
import app.sport.sw.domain.meeting.MeetingUser;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.meeting.RequestCreateMeeting;
import app.sport.sw.dto.meeting.RequestEditMeeting;
import app.sport.sw.dto.meeting.ResponseMeetingView;
import app.sport.sw.exception.MeetingException;
import app.sport.sw.repository.ClubRepository;
import app.sport.sw.repository.MeetingRepository;
import app.sport.sw.repository.UserRepository;
import app.sport.sw.response.MeetingError;
import app.sport.sw.wrappers.MeetingWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final FileService fileService;

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final MeetingRepository meetingRepository;

    private final MeetingWrapper meetingWrapper;

    @Transactional(readOnly = true)
    @Override
    public List<ResponseMeetingView> findAllByClubIdAsMeetingView(long clubId) {
        return meetingRepository.findAllByClubId(clubId)
            .stream()
            .map(meetingWrapper::meetingListViewWrap)
            .toList();
    }

    @Override
    public ResponseMeetingView findByMeetingIdAsMeetingView(long meetingId) {
        return meetingWrapper.meetingListViewWrap(findById(meetingId));
    }

    @Override
    public void createMeeting(RequestCreateMeeting createMeeting, long clubId, long userId) {

        Club club = clubFindById(clubId);
        User user = userFindById(userId);

        Meeting saveMeeting = Meeting.builder()
            .club(club)
            .user(user)
            .title(createMeeting.getTitle())
            .description(createMeeting.getDescription())
            .meetingDate(createMeeting.getMeetingDate())
            .meetingImage(new MeetingImage())
            .build();

        meetingRepository.saveMeeting(saveMeeting);

    }

    @Override
    public synchronized void joinMeeting(long meetingId, long userId) {
        boolean alreadyJoin = meetingRepository.existsByMeetingIdAndUserId(meetingId, userId);
        if (alreadyJoin) throw new MeetingException(MeetingError.MEETING_ALREADY_JOIN);

        Meeting meeting = findById(meetingId);
        User user = userFindById(userId);

        MeetingUser saveMeetingUser = MeetingUser.builder()
            .meeting(meeting)
            .user(user)
            .build();

        meetingRepository.saveMeetingUser(saveMeetingUser);

    }

    @Override
    public void editMeeting(long meetingId, RequestEditMeeting editMeeting) {
        Meeting meeting = findById(meetingId);

        if (editMeeting.getImage() != null) {
            fileService.editImage(editMeeting.getImage(), meeting.getMeetingImage(), FileType.MEETING_IMAGE);
        }
        if (editMeeting.getTitle() != null) {
            meeting.setTitle(editMeeting.getTitle());
        }
        if (editMeeting.getDescription() != null) {
            meeting.setDescription(editMeeting.getDescription());
        }
        if (editMeeting.getMeetingDate() != null) {
            meeting.setMeetingDate(editMeeting.getMeetingDate());
        }
    }

    @Override
    public void deleteMeeting(long meetingId) {
        Meeting meeting = findById(meetingId);
        fileService.deleteImage(meeting.getMeetingImage());
        meetingRepository.deleteBy(meeting);
    }

    @Override
    public void exitMeeting(long meetingId, long userId) {
        meetingRepository.deleteByMeetingUser_MeetingIdAndUserId(meetingId, userId);
    }

    private Meeting findById(long meetingId) {
        return meetingRepository.findById(meetingId);
    }

    private Club clubFindById(long clubId) {
        return clubRepository.findById(clubId);
    }

    private User userFindById(long userId) {
        return userRepository.findByUserId(userId);
    }
}
