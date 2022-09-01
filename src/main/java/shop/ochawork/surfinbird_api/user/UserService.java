package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ochawork.surfinbird_api.user.dto.UserInfoDto;
import shop.ochawork.surfinbird_api.user.dto.UserLoginDto;
import shop.ochawork.surfinbird_api.user.dto.UserMapper;
import shop.ochawork.surfinbird_api.user.response.LoginResponse;
import shop.ochawork.surfinbird_api.user.response.RegisterResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserDataAccessService userDataAccessService;

    @Autowired
    public UserService(UserDataAccessService userDataAccessService) {
        this.userDataAccessService = userDataAccessService;
    }

    public User getUserById(UUID userId){
        return userDataAccessService.selectUserById(userId);
    }

    public RegisterResponse userRegister(User user){
        return userDataAccessService.insertUser(user);
    }

    public User getUserByEmail(String email) {
        return userDataAccessService.selectUserByEmail(email);
    }

//    public List<UserInfoDto> getUserByKeyword(String word) {
//        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
//        userDataAccessService.selectUserByKeyword(word).stream().forEach(user -> {
//            userInfoDtoList.add(userMapper.toUserInfoDto(user));
//        });
//        return userInfoDtoList;
//    }

    public LoginResponse loginValid(UserLoginDto userLoginDto) {
        try {
            if (getUserByEmail(userLoginDto.getEmail()).getPassword().equals(
                    userLoginDto.getPassword()))
                return new LoginResponse(getUserByEmail(userLoginDto.getEmail()).getUserId(),
                        "Successful");
            else return new LoginResponse(null, "Wrong_password");
        } catch (Exception e) {
            return new LoginResponse(null, "Wrong_email");
        }
    }
}
