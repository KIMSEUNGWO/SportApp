package app.sport.sw.mvc.club;

import app.sport.sw.dto.group.ResponseDefaultGroupData;

public interface ClubService {

    ResponseDefaultGroupData getClubData(long clubId);
}
