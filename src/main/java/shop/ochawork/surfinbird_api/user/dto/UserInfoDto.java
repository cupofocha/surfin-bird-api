package shop.ochawork.surfinbird_api.user.dto;

import java.util.UUID;

public class UserInfoDto {
    private String displayName;

    private String email;

    private UUID userId;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserInfoDto(String displayName, String email, UUID userId) {
        this.displayName = displayName;
        this.email = email;
        this.userId = userId;
    }
}
