package shop.ochawork.surfinbird_api.user.response;

import java.util.UUID;

public class RegisterResponse {
    private UUID userId;
    private String State;

    public UUID getUserId() {
        return userId;
    }

    public void setEmail(UUID userId) {
        this.userId = userId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public RegisterResponse(UUID userId, String state) {
        this.userId = userId;
        State = state;
    }
}
