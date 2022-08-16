package shop.ochawork.surfinbird.user.dto;

import shop.ochawork.surfinbird.user.User;

import java.util.UUID;

public class UserMapper {
    public UserInfoDto toUserInfoDto(User user) {
        return new UserInfoDto(user.getDisplayName(), user.getEmail(), user.getUserId());
    }

    public User createUser(UserCreationDto newUser) {
        return new User(UUID.randomUUID(), newUser.getDisplayName(), newUser.getPassword(), newUser.getEmail(), newUser.getRole());
    }

    public User userWithNewPwd(User user, ChangePasswordDao changePasswordDao){
        return new User(user.getUserId(), user.getDisplayName(), user.getPassword(), user.getEmail(), user.getRole());
    }
}
