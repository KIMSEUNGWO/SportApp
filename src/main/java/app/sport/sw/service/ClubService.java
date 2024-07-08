package app.sport.sw.service;

import app.sport.sw.dto.group.ResponseDefaultGroupData;

public interface ClubService {

    ResponseDefaultGroupData getClubData(long clubId);
}
