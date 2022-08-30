package shop.ochawork.surfinbird_api.user.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.image.AvatarImage;
import shop.ochawork.surfinbird_api.image.BirdImageService;
import shop.ochawork.surfinbird_api.user.User;
import shop.ochawork.surfinbird_api.user.UserService;

import java.util.UUID;

@Component
public class UserMapper {
    private final UserService userService;
    private final BirdImageService birdImageService;

    @Autowired
    public UserMapper(UserService userService, BirdImageService birdImageService) {
        this.userService = userService;
        this.birdImageService = birdImageService;
    }

    public UserInfoDto toUserInfoDto(User user) {
        return new UserInfoDto(user.getDisplayName(), user.getEmail(), user.getUserId());
    }

    public User toUser(UserRegisterDto newUser) {
        return new User(UUID.randomUUID(), newUser.getDisplayName(), newUser.getPassword(), newUser.getEmail(), newUser.getRole());
    }

    public UserLoginDto userLoginDto(User user) {
        return new UserLoginDto(user.getEmail(), user.getPassword());
    }

    public User userWithNewPwd(User user, ChangePasswordDto changePasswordDao) {
        return new User(user.getUserId(), user.getDisplayName(), user.getPassword(), user.getEmail(), user.getRole());
    }

    public UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(
                user.getDisplayName(),
                user.getEmail(),
                new AvatarImage(0, user.getUserId(), "0"),
                birdImageService.getImagesByUploaderId(user.getUserId()));
    }
}
