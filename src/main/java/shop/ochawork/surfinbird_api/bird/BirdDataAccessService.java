package shop.ochawork.surfinbird_api.bird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BirdDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BirdDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Bird> mapBirdFromDb() {
        return (resultSet, i) -> {
            String englishName = resultSet.getString("english_name");
            String scientificName = resultSet.getString("scientific_name");

            return new Bird(englishName, scientificName);
        };
    }

    public List<Bird> selectAllBirds() {
        String sql =
                "SELECT " +
                " english_name, " +
                " scientific_name " +
                " FROM bird";

        return jdbcTemplate.query(sql, mapBirdFromDb());
    }

    public Bird selectBirdByName(String name) {
        String sql =
                "SELECT " +
                " * " +
                " FROM bird " +
                " WHERE english_name = ? " +
                " OR scientific_name = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{name, name}, mapBirdFromDb());
    }

    int insertBird(Bird bird) {
        if (birdExists(bird.getEnglishName()) && birdExists(bird.getScientificName())) {
            throw new IllegalArgumentException();
        }

        String sql =
                "INSERT INTO bird (" +
                "english_name, " +
                "scientific_name) " +
                "VALUES (?,?)" ;

        return jdbcTemplate.update(sql, bird.getEnglishName(), bird.getScientificName());
    }

    boolean birdExists(String name) {
        String sql =
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM bird " +
                " WHERE english_name = ? " +
                " OR scientific_name = ?)";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{name, name},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
}
