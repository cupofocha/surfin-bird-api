package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shop.ochawork.surfinbird_api.user.respone.RegisterState;

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

    User selectUserById(UUID userId) {
        String sql =
                "SELECT " +
                " * " +
                " FROM users " +
                " WHERE user_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, mapUserFromDb());
    }

    User selectUserByEmail(String email) {
        String sql =""+
                "SELECT " +
                " * " +
                " FROM users " +
                " WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email}, mapUserFromDb());
    }

    public RegisterState insertUser(User user) {
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
                return new RegisterState(user.getUserId(), "Success");
            }
            else return new RegisterState(selectUserByEmail(user.getEmail()).getUserId(), "User-exists");
        }
        catch (Exception e) {
            return new RegisterState(null, "Failed-unexpected-exception");
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
