package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ochawork.surfinbird_api.user.dto.UserLoginDto;
import shop.ochawork.surfinbird_api.user.dto.UserProfileDto;
import shop.ochawork.surfinbird_api.user.response.LoginResponse;
import shop.ochawork.surfinbird_api.user.response.RegisterResponse;

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

    public RegisterResponse userRegister(User user){
        return userDataAccessService.insertUser(user);
    }

    public User getUserByEmail(String email) {
        return userDataAccessService.selectUserByEmail(email);
    }

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
