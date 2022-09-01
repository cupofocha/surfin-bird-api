package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shop.ochawork.surfinbird_api.user.dto.UserInfoDto;
import shop.ochawork.surfinbird_api.user.dto.UserMapper;
import shop.ochawork.surfinbird_api.user.response.RegisterResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> mapUserFromDb() {
        return (resultSet, i) -> {
            return new User(
                    UUID.fromString(resultSet.getString("user_id")),
                    resultSet.getString("display_name"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    Role.valueOf(resultSet.getString("user_role"))
            );
        };
    }

    public User selectUserById(UUID userId) {
        String sql =
                "SELECT " +
                " * " +
                " FROM users " +
                " WHERE user_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, mapUserFromDb());
    }

    public User selectUserByEmail(String email) {
        String sql =""+
                "SELECT " +
                " * " +
                " FROM users " +
                " WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email}, mapUserFromDb());
    }

    public List<UserInfoDto> selectUserByKeyword(String word) {
        String sql =""+
                " SELECT * " +
                " FROM users" +
                " WHERE (display_name ILIKE '%" + word + "%'" +
                " OR email ILIKE '%" + word + "%')";
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        jdbcTemplate.query(sql, mapUserFromDb()).stream().forEach(user -> {
            userInfoDtoList.add(new UserInfoDto(user.getDisplayName(),
                    user.getEmail(),
                    user.getUserId()));
        });
        return userInfoDtoList;
    }

    public RegisterResponse insertUser(User user) {
        try {
            String sql =""+
                    "INSERT INTO users(" +
                    "user_id, " +
                    "display_name, " +
                    "password, " +
                    "email, " +
                    "user_role) " +
                    "VALUES (?,?,?,?,?)";

            if(!userExists(user.getEmail())){
                jdbcTemplate.update(sql, user.getUserId(), user.getDisplayName(), user.getPassword(), user.getEmail(), user.getRole().name());
                return new RegisterResponse(user.getUserId(), "Success");
            }
            else return new RegisterResponse(selectUserByEmail(user.getEmail()).getUserId(), "User-exists");
        }
        catch (Exception e) {
            return new RegisterResponse(null, "Failed-unexpected-exception");
        }
    }

    boolean userExists(String email) {
        String sql =
                "SELECT EXISTS ( " +
                        " SELECT 1 " +
                        " FROM users " +
                        " WHERE email = ?) ";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{email},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
}