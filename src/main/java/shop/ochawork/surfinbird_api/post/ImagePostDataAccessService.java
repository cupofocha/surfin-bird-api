package shop.ochawork.surfinbird_api.post;

import org.tinylog.Logger;
import shop.ochawork.surfinbird_api.comment.CommentDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shop.ochawork.surfinbird_api.image.BirdImageDateAccessService;
import shop.ochawork.surfinbird_api.index.IndexDataAccessServer;

import java.util.List;
import java.util.UUID;

@Repository
public class ImagePostDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    private final BirdImageDateAccessService birdImageDateAccessService;

    private final CommentDataAccessService commentDataAccessService;

    private final IndexDataAccessServer indexDataAccessServer;

    @Autowired
    public ImagePostDataAccessService(JdbcTemplate jdbcTemplate, BirdImageDateAccessService birdImageDateAccessService, CommentDataAccessService commentDataAccessService, IndexDataAccessServer indexDataAccessServer) {
        this.jdbcTemplate = jdbcTemplate;
        this.birdImageDateAccessService = birdImageDateAccessService;
        this.commentDataAccessService = commentDataAccessService;
        this.indexDataAccessServer = indexDataAccessServer;
    }

    private RowMapper<ImagePost> mapImagePostFromDb() {
        return (resultSet, i) -> {
            return new ImagePost(
                    resultSet.getInt("id"),
                    UUID.fromString(resultSet.getString("poster_id")),
                    resultSet.getString("text"),
                    birdImageDateAccessService.selectImageById(resultSet.getInt("bird_image_id")),
                    commentDataAccessService.selectCommentsByPostId("image", resultSet.getInt("id"))
            );
        };
    }

    private long newId() {
        long index = indexDataAccessServer.selectIndexByType("Image Post");
        indexDataAccessServer.updateIndexByType("Image Post", ++index);
        return index;
    }

    private int numOfPosts(){
        String sql = "SELECT COUNT(*) FROM image_post";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public long insertImagePost(UUID posterId, String text, long birdImageId) {
        String sql =""+
                "INSERT INTO image_post(" +
                "id, " +
                "poster_id, " +
                "text, " +
                "bird_image_id) " +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, newId(), posterId, text, birdImageId);
        return indexDataAccessServer.selectIndexByType("Image Post");
    }

    public ImagePost selectPostById(long id) {
        String sql =""+
                "SELECT " +
                " * " +
                " FROM image_post " +
                " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapImagePostFromDb());
    }

    public List<ImagePost> selectPostBySearch(String word) {
        String sql =""+
                " SELECT * " +
                " FROM image_post" +
                " WHERE text ILIKE '%" + word + "%'";
        System.out.println(sql);
        return jdbcTemplate.query(sql, mapImagePostFromDb());
    }

    public PostResponse updateTextById(long id, String text) {
        try {
            String sql = "" +
                    " UPDATE image_post " +
                    " SET text = ? " +
                    " WHERE id = ? ";
            jdbcTemplate.update(sql, text, id);
            return new PostResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new PostResponse(id, e.toString());
        }
    }

    public PostResponse deletePostById(long id) {
        try {
            String sql = "" +
                    " DELETE " +
                    " FROM image_post " +
                    " WHERE id = ? ";
            jdbcTemplate.update(sql, id);
            return new PostResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new PostResponse(id, e.toString());
        }
    }
}
