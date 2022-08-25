package shop.ochawork.surfinbird_api.user.dto;

import shop.ochawork.surfinbird_api.user.Role;

public class UserRegisterDto {

    private String displayName;

    private String password;

    private String email;

    private Role role;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRegisterDto(String displayName, String password, String email, Role role) {
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
