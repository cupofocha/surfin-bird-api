package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.user.dto.UserInfoDto;
import shop.ochawork.surfinbird_api.user.dto.UserLoginDto;
import shop.ochawork.surfinbird_api.user.dto.UserRegisterDto;
import shop.ochawork.surfinbird_api.user.dto.UserMapper;
import shop.ochawork.surfinbird_api.user.respone.LoginState;
import shop.ochawork.surfinbird_api.user.respone.RegisterState;

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
    public RegisterState userRegister (@RequestBody UserRegisterDto userRegisterDto) {
        return userService.userRegister(userMapper.toUser(userRegisterDto));
    }

    @PostMapping(path = "login")
    @ResponseBody
    public LoginState userLogin (@RequestBody UserLoginDto userLoginDto) {
        System.out.println("Email: " + userLoginDto.getEmail());
        System.out.println("Password: " + userLoginDto.getPassword());
        return userService.loginValid(userLoginDto);
    }

    @GetMapping(path = "{userId}")
    public UserInfoDto getUserInfo (@PathVariable("userId") UUID userId){
        return userMapper.toUserInfoDto(userService.getUserById(userId));
    }
}
