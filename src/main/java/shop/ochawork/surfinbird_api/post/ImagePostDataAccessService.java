package shop.ochawork.surfinbird_api.post;

import shop.ochawork.surfinbird_api.comment.CommentDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shop.ochawork.surfinbird_api.image.BirdImageDateAccessService;

import java.util.UUID;

@Repository
public class ImagePostDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    private final BirdImageDateAccessService birdImageDateAccessService;

    private final CommentDataAccessService commentDataAccessService;

    @Autowired
    public ImagePostDataAccessService(JdbcTemplate jdbcTemplate, BirdImageDateAccessService birdImageDateAccessService, CommentDataAccessService commentDataAccessService) {
        this.jdbcTemplate = jdbcTemplate;
        this.birdImageDateAccessService = birdImageDateAccessService;
        this.commentDataAccessService = commentDataAccessService;
    }

    private RowMapper<ImagePost> mapImagePostFromDb() {
        return (resultSet, i) -> {
            return new ImagePost(
                    resultSet.getInt("id"),
                    UUID.fromString(resultSet.getString("poster_id")),
                    resultSet.getString("text"),
                    birdImageDateAccessService.selectImageById(resultSet.getInt("bird_image_id")),
                    commentDataAccessService.selectCommentsByPostId("Image", resultSet.getInt("id"))
            );
        };
    }

    private int numOfPosts(){
        String sql = "SELECT COUNT(*) FROM image_post";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int insertImagePost(UUID posterId, String text, long birdImageId) {
        String sql =""+
                "INSERT INTO image_post(" +
                "id, " +
                "poster_id, " +
                "text, " +
                "bird_image_id) " +
                "VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, numOfPosts(), posterId, text, birdImageId);
    }

    public ImagePost selectPostById(long id) {
        String sql =""+
                "SELECT " +
                " * " +
                " FROM image_post " +
                " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapImagePostFromDb());
    }
}
