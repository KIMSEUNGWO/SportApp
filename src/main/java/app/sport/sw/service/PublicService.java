package app.sport.sw.service;

import app.sport.sw.dto.user.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;

public interface PublicService {
    CustomUserDetails getSecurityContextUserDetails(HttpServletRequest request);
}
