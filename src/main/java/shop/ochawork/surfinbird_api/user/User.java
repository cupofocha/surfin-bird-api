package shop.ochawork.surfinbird_api.user;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

public class User {
    private UUID userId;

    @NotBlank
    private String displayName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private Role role;

    public User(UUID userId, String displayName, String password, String email, Role role) {
        this.userId = userId;
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
