package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.user.dto.*;
import shop.ochawork.surfinbird_api.user.response.LoginResponse;
import shop.ochawork.surfinbird_api.user.response.RegisterResponse;
import shop.ochawork.surfinbird_api.user.response.UserResponse;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "register")
    @ResponseBody
    public RegisterResponse userRegister (@RequestBody UserRegisterDto userRegisterDto) {
        return userService.userRegister(userMapper.toUser(userRegisterDto));
    }

    @PostMapping(path = "login")
    @ResponseBody
    public LoginResponse userLogin (@RequestBody UserLoginDto userLoginDto) {
        System.out.println("Email: " + userLoginDto.getEmail());
        System.out.println("Password: " + userLoginDto.getPassword());
        return userService.loginValid(userLoginDto);
    }

    @GetMapping(path = "{userId}")
    public UserInfoDto getUserInfo (@PathVariable("userId") UUID userId) {
        return userMapper.toUserInfoDto(userService.getUserById(userId));
    }

    @GetMapping(path = "profile/{userId}")
    public UserProfileDto getUserProfile (@PathVariable("userId") UUID userId) {
        return userMapper.toUserProfileDto(userService.getUserById(userId));
    }

    @PostMapping(path = "change/display_name")
    public UserResponse changeDisplayName(@RequestBody ChangeDisplayNameDto changeDisplayNameDto) {
        return userService.changeDisplayName(changeDisplayNameDto.getId(), changeDisplayNameDto.getDisplayName());
    }

    @PostMapping(path = "change/email")
    public UserResponse changeEmail(@RequestBody ChangeEmailDto changeEmailDto) {
        return userService.changeEmail(changeEmailDto.getId(), changeEmailDto.getNewEmail());
    }
}
