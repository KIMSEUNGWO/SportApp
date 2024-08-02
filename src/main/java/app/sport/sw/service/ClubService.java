package app.sport.sw.service;

import app.sport.sw.dto.club.*;
import app.sport.sw.dto.user.CustomUserDetails;

import java.util.List;

public interface ClubService {

    DefaultClubInfo getClubData(long clubId, CustomUserDetails userDetails);

    long createClub(CustomUserDetails userDetails, ClubCreateRequest clubCreateRequest);

    List<ClubListView> findByClubs(List<Long> clubIds);

    void editClub(long clubId, ClubEditRequest clubEditRequest);

    void joinClub(long clubId, CustomUserDetails userDetails);

    List<ClubListView> getMyClubs(long userId);

    List<ResponseClubUser> getClubUsers(long clubId);

    void exitClub(long clubId, CustomUserDetails userDetails);
}
