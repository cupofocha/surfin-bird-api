package shop.ochawork.surfinbird_api.user.dto;

import java.util.UUID;

public class ChangeEmailDto {
    private UUID id;

    private String newEmail;

    public ChangeEmailDto(UUID id, String newEmail) {
        this.id = id;
        this.newEmail = newEmail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
