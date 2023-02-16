package shop.ochawork.surfinbird_api.user.dto;

import java.util.UUID;

public class ChangeDisplayNameDto {
    private UUID id;

    private String displayName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ChangeDisplayNameDto(UUID id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }
}
