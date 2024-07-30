package app.sport.sw.domain.meeting;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.domain.group.Club;
import app.sport.sw.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "MEETING")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Meeting extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEETING_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Setter
    private String title;
    @Setter
    private String description;

    @Setter
    private LocalDateTime meetingDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEETING_IMAGE_ID")
    private MeetingImage meetingImage;

    @OneToMany(mappedBy = "meeting", orphanRemoval = true)
    private List<MeetingUser> meetingUserList = new ArrayList<>();


}
