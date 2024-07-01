package app.sport.sw.domain.group;

import app.sport.sw.domain.BaseEntityTime;
import app.sport.sw.enums.AccessType;
import app.sport.sw.domain.group.region.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "CLUB")
@AllArgsConstructor
@NoArgsConstructor
public class Club extends BaseEntityTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_ID")
    private long id;

    private String title;
    private String intro;

    @Embedded
    private Region region;
    private char secret;
    private String inviteCode;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLUB_IMAGE_ID")
    private ClubImage clubImage;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<UserClub> userClubList = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<ClubJoinRequest> clubJoinRequestList = new ArrayList<>();

}
