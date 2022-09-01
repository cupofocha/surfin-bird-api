package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;
import shop.ochawork.surfinbird_api.index.IndexDataAccessServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BirdImageDateAccessService {
    private final JdbcTemplate jdbcTemplate;

    private final IndexDataAccessServer indexDataAccessServer;

    @Autowired
    public BirdImageDateAccessService(JdbcTemplate jdbcTemplate, IndexDataAccessServer indexDataAccessServer) {
        this.jdbcTemplate = jdbcTemplate;
        this.indexDataAccessServer = indexDataAccessServer;
    }

    public int numOfImages() {
        String sql = "SELECT COUNT(*) FROM bird_image";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    private long newId() {
        long index = indexDataAccessServer.selectIndexByType("Bird Image");
        indexDataAccessServer.updateIndexByType("Bird Image", ++index);
        return index;
    }

    public BirdImage getImageByPath(String path) {
        String sql = ""+
                " SELECT * " +
                " FROM bird_image " +
                " WHERE path = ? ";

        return jdbcTemplate.queryForObject(sql, new Object[]{path}, mapImageFromDb());
    }

    public int insertImage(UUID userId, String path, Boolean approvement, long postId) {
        try{
            if(birdImageExists(path)){
                throw new IllegalArgumentException();
            }
        }
        catch (Exception e){
            Logger.info("Image already exists!");
            return 0;
        }

        String sql =""+
                "INSERT INTO bird_image(" +
                "id, " +
                "bird, " +
                "path, " +
                "approvement, " +
                "uploader_id, " +
                "post_id) " +
                "VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, newId(), "NULL", path, approvement, userId, postId);
    }

    public List<BirdImage> selectAllImages() {
        try {
            String sql = "" +
                    "SELECT " +
                    " * " +
                    " FROM bird_image";
            return jdbcTemplate.query(sql, mapImageFromDb());
        }
        catch (Exception e) {
            Logger.error(e);
            return new ArrayList<>();
        }
    }

    public BirdImage selectImageById(long id) {

        try {
            String sql = "" +
                    " SELECT * " +
                    " FROM bird_image " +
                    " WHERE id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapImageFromDb());
        }
        catch (Exception e) {
            Logger.error(e);
            return null;
        }
    }

    public BirdImage selectImageByPostId(long postId) {

        try {
            String sql = "" +
                    " SELECT * " +
                    " FROM bird_image " +
                    " WHERE post_id = ? ";
            return jdbcTemplate.queryForObject(sql, new Object[]{postId}, mapImageFromDb());
        }
        catch (Exception e) {
            Logger.error(e);
            return null;
        }
    }

    public BirdImageResponse deleteImageById(long id) {

        try {
            String sql = "" +
                    " DELETE " +
                    " FROM bird_image " +
                    " WHERE id = ? ";
            jdbcTemplate.update(sql, id);
            return new BirdImageResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new BirdImageResponse(id, e.toString());
        }
    }

    public BirdImageResponse deleteImageByPostId(long postId) {

        try {
            String sql = "" +
                    " DELETE " +
                    " FROM bird_image " +
                    " WHERE post_id = ? ";
            jdbcTemplate.update(sql, postId);
            return new BirdImageResponse(postId, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new BirdImageResponse(postId, e.toString());
        }
    }

    public int updateImagePostIdById(long id, long postId) {
        try {
            String sql = "" +
                    " UPDATE bird_image " +
                    " SET post_id = ? " +
                    " WHERE id = ? ";
            return jdbcTemplate.update(sql, postId, id);
        }
        catch (Exception e) {
            Logger.error(e);
            return 0;
        }
    }

    boolean birdImageExists(String path) {
        String sql =
                "SELECT EXISTS ( " +
                        " SELECT 1 " +
                        " FROM bird_image " +
                        " WHERE path = ?) ";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{path},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }

    public List<BirdImage> selectBirdImagesByUploaderId(UUID userId) {
        try {
            String sql = "" +
                    " SELECT * " +
                    " FROM bird_image " +
                    " WHERE uploader_id = ? ";

            return jdbcTemplate.query(sql, new Object[]{userId}, mapImageFromDb());
        }
        catch (Exception e) {
            Logger.error(e);
            return new ArrayList<>();
        }
    }

    public BirdImageResponse updatePathById(long id, String path) {
        try {
            String sql = "" +
                    " UPDATE bird_image " +
                    " SET path = ? " +
                    " WHERE id = ? ";
            jdbcTemplate.update(sql, path, id);
            return new BirdImageResponse(id, "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new BirdImageResponse(id, e.toString());
        }
    }

    private RowMapper<BirdImage> mapImageFromDb() {
        return (resultSet, i) -> {
            long id = resultSet.getInt("id");
            String bird = resultSet.getString("bird");
            String path = resultSet.getString("path");
            boolean approvement = resultSet.getBoolean("approvement");
            UUID uploaderId = UUID.fromString(resultSet.getString("uploader_id"));
            long postId = resultSet.getInt("post_id");

            return new BirdImage(id, bird, path, approvement, uploaderId, postId);
        };
    }
}
