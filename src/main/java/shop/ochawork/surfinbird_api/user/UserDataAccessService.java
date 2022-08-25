package shop.ochawork.surfinbird_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    public int insertUser(User user) {
        String sql =
                "INSERT INTO users(" +
                "user_id, " +
                "display_name, " +
                "password, " +
                "email, " +
                "user_role) " +
                "VALUES (?,?,?,?,?)" ;

        return jdbcTemplate.update(sql, user.getUserId(), user.getDisplayName(), user.getPassword(), user.getEmail(), user.getRole().name());
    }
}
