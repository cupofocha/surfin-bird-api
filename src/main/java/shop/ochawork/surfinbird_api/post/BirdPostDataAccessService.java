package shop.ochawork.surfinbird_api.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BirdPostDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BirdPostDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int numOfPosts(){
        String sql = "SELECT COUNT(*) FROM bird_post";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
