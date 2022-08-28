package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CommentDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Comment> mapCommentFromDb() {
        return (resultSet, i) -> {
            System.out.println("Comments below");
            System.out.println(resultSet.getString("text"));
            return new Comment(
                    resultSet.getInt("id"),
                    UUID.fromString(resultSet.getString("commenter_id")),
                    resultSet.getString("text")
            );
        };
    }

    public int numOfComments(){
        String sql = "SELECT COUNT(*) FROM comment";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int insertComment(UUID commenterId, String text, String postType, long postId){
        String sql =""+
                "INSERT INTO comment(" +
                "id, " +
                "commenter_id, " +
                "text, " +
                "post_type, " +
                "post_id) " +
                "VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, numOfComments(), commenterId, text, postType, postId);
    }

    public List<Comment> selectCommentsByPostId(String postType, long postId) {
        String sql = ""+
                " SELECT * " +
                " FROM comment " +
                " WHERE (post_id = ? " +
                " AND post_type = ?)";

        System.out.println("Getting comments for: "+postType+postId);
        return jdbcTemplate.query(sql, new Object[]{postId, postType}, mapCommentFromDb());
    }
}
