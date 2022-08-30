package shop.ochawork.surfinbird_api.user.response;

import java.util.UUID;

public class LoginResponse {
    private UUID userId;
    private String State;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public LoginResponse(UUID userId, String state) {
        this.userId = userId;
        State = state;
    }
}
