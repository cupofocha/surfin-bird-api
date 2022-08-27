package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BirdImageDateAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BirdImageDateAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int numOfImages(){
        String sql = "SELECT COUNT(*) FROM bird_image";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int insertImage(UUID userId, String path, Boolean approvement) {
        try{
            if(birdImageExists(path)){
                throw new IllegalArgumentException();
            }
        }
        catch (Exception e){
            System.out.println("Image already exists!");
            return 0;
        }

        String sql =""+
                "INSERT INTO bird_image (" +
                "id, " +
                "bird, " +
                "path, " +
                "approvement, " +
                "uploader_id) " +
                "VALUES (?,?,?,?,?)" ;
        return jdbcTemplate.update(sql, numOfImages(), "NULL", path, approvement, userId);
    }

    public List<BirdImage> selectAllImages() {
        String sql =""+
                "SELECT " +
                " id, " +
                " bird, " +
                " path, " +
                " approvement, " +
                " uploader_id " +
                " FROM bird_image";

        return jdbcTemplate.query(sql, mapImageFromDb());
    }

    public BirdImage selectImageById(long id) {
        String sql = ""+
                " SELECT * " +
                " FROM bird_image " +
                " WHERE id = ? ";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapImageFromDb());
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

    private RowMapper<BirdImage> mapImageFromDb() {
        return (resultSet, i) -> {
            long id = resultSet.getInt("id");
            String bird = resultSet.getString("bird");
            String path = resultSet.getString("path");
            boolean approvement = resultSet.getBoolean("approvement");
            UUID uploaderId = UUID.fromString(resultSet.getString("uploader_id"));

            return new BirdImage(id, bird, path, approvement, uploaderId);
        };
    }
}
