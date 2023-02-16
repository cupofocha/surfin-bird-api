package shop.ochawork.surfinbird_api.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;
import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.comment.CommentResponse;

@Repository
public class IndexDataAccessServer {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IndexDataAccessServer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long selectIndexByType(String type) {
        try{
            String sql = ""+
                    " SELECT index " +
                    " FROM index_table " +
                    " WHERE type = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{type}, Long.class);
        }
        catch (Exception e){
            return 0;
        }
    }

    public int updateIndexByType(String type, long index) {
        try {
            String sql = ""+
                    " UPDATE index_table " +
                    " SET index = ? " +
                    " WHERE type = ? ";
            jdbcTemplate.update(sql, index, type);
            return 1;
        }
        catch (Exception e) {
            Logger.error(e);
            return 0;
        }
    }
}
