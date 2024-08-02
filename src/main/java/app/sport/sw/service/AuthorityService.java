package app.sport.sw.service;

import app.sport.sw.domain.group.UserClub;

public interface AuthorityService {

    void delegateClubOwner(UserClub userClub);
}
