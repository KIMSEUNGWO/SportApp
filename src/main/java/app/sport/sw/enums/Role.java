package app.sport.sw.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("ROLE_USER", "사용자"),
    VIP("ROLE_VIP_USER", "VIP"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String roleName;
    private final String title;

}