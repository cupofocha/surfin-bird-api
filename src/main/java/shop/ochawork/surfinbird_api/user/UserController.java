package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.user.dto.UserRegisterDto;
import shop.ochawork.surfinbird_api.user.dto.UserMapper;

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

    @PostMapping(path="register")
    @ResponseBody
    public int userRegister (@RequestBody UserRegisterDto userRegisterDto) {
        return userService.userRegister(userMapper.toUser(userRegisterDto));
    }
}
