package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int userRegister(User user){
        return userDataAccessService.insertUser(user);
    }
}
