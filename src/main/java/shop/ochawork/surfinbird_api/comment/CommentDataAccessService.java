package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;
import shop.ochawork.surfinbird_api.index.IndexDataAccessServer;

import java.util.List;
import java.util.UUID;

@Repository
public class CommentDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    private final IndexDataAccessServer indexDataAccessServer;

    @Autowired
    public CommentDataAccessService(JdbcTemplate jdbcTemplate, IndexDataAccessServer indexDataAccessServer) {
        this.jdbcTemplate = jdbcTemplate;
        this.indexDataAccessServer = indexDataAccessServer;
    }

    private RowMapper<Comment> mapCommentFromDb() {
        return (resultSet, i) -> {
            return new Comment(
                    resultSet.getInt("id"),
                    UUID.fromString(resultSet.getString("commenter_id")),
                    resultSet.getString("text")
            );
        };
    }

    private long newId() {
        long index = indexDataAccessServer.selectIndexByType("Comment");
        indexDataAccessServer.updateIndexByType("Comment", ++index);
        return index;
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
        return jdbcTemplate.update(sql, newId(), commenterId, text, postType, postId);
    }

    public List<Comment> selectCommentsByPostId(String postType, long postId) {
        String sql = ""+
                " SELECT * " +
                " FROM comment " +
                " WHERE (post_id = ? " +
                " AND post_type = ?)";

        return jdbcTemplate.query(sql, new Object[]{postId, postType}, mapCommentFromDb());
    }

    public List<Comment> selectCommentsByType(String postType) {
        String sql = ""+
                " SELECT * " +
                " FROM comment " +
                " WHERE post_type = ? ";

        return jdbcTemplate.query(sql, new Object[]{postType}, mapCommentFromDb());
    }

    public Comment selectCommentById(long id) {
        String sql = ""+
                " SELECT * " +
                " FROM comment " +
                " WHERE id = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapCommentFromDb());
    }

    public CommentResponse deleteCommentById(long id) {
        try {
            String sql = ""+
                    " DELETE " +
                    " FROM comment " +
                    " WHERE id = ? ";
            jdbcTemplate.update(sql, id);
            return new CommentResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new CommentResponse(id, e.toString());
        }
    }

    public CommentResponse updateText(String text, String postType, long id) {
        try {
            String sql = ""+
                    " UPDATE comment " +
                    " SET text = ? " +
                    " WHERE (id = ? " +
                    " AND post_type = ?)";
            jdbcTemplate.update(sql, text, id, postType);
            return new CommentResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new CommentResponse(id, e.toString());
        }
    }
}
