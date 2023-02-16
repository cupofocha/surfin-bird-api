package shop.ochawork.surfinbird_api.user.response;

import java.util.UUID;

public class UserResponse {
    private UUID id;

    private String state;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserResponse(UUID id, String state) {
        this.id = id;
        this.state = state;
    }
}
