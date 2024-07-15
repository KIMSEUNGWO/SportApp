package app.sport.sw.service;

import app.sport.sw.dto.club.ResponseDefaultClubData;

public interface ClubService {

    ResponseDefaultClubData getClubData(long clubId);
}
