package app.sport.sw.service;

import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.club.RecentlyViewClub;
import app.sport.sw.dto.user.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ClubService {

    DefaultClubInfo getClubData(long clubId, CustomUserDetails userDetails);

    long createClub(CustomUserDetails userDetails, ClubCreateRequest clubCreateRequest);

    List<RecentlyViewClub> findByClubs(List<Long> clubIds);
}
