package shop.ochawork.surfinbird_api.user.dto;

import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.user.User;

import java.util.UUID;

@Component
public class UserMapper {
    public UserInfoDto toUserInfoDto(User user) {
        return new UserInfoDto(user.getDisplayName(), user.getEmail(), user.getUserId());
    }

    public User toUser(UserRegisterDto newUser) {
        return new User(UUID.randomUUID(), newUser.getDisplayName(), newUser.getPassword(), newUser.getEmail(), newUser.getRole());
    }

    public UserLoginDto userLoginDto(User user) {
        return new UserLoginDto(user.getEmail(), user.getPassword());
    }

    public User userWithNewPwd(User user, ChangePasswordDao changePasswordDao) {
        return new User(user.getUserId(), user.getDisplayName(), user.getPassword(), user.getEmail(), user.getRole());
    }
}
