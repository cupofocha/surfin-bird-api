package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ochawork.surfinbird_api.user.dto.UserInfoDto;
import shop.ochawork.surfinbird_api.user.dto.UserLoginDto;
import shop.ochawork.surfinbird_api.user.respone.LoginState;
import shop.ochawork.surfinbird_api.user.respone.RegisterState;

import java.util.UUID;

@Service
public class UserService {
    UserDataAccessService userDataAccessService;

    @Autowired
    public UserService(UserDataAccessService userDataAccessService) {
        this.userDataAccessService = userDataAccessService;
    }

    public User getUserById(UUID userId){
        return userDataAccessService.selectUserById(userId);
    }

    public RegisterState userRegister(User user){
        return userDataAccessService.insertUser(user);
    }

    public User getUserByEmail(String email) {
        return userDataAccessService.selectUserByEmail(email);
    }

    public LoginState loginValid(UserLoginDto userLoginDto) {
        try {
            if (getUserByEmail(userLoginDto.getEmail()).getPassword().equals(
                    userLoginDto.getPassword()))
                return new LoginState(getUserByEmail(userLoginDto.getEmail()).getUserId(),
                        "Successful");
            else return new LoginState(null, "Wrong_password");
        } catch (Exception e) {
            return new LoginState(null, "Wrong_email");
        }
    }
}
