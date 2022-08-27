package shop.ochawork.surfinbird_api.image;

import javax.validation.Path;
import java.util.UUID;

public class BirdImage {
    private long id;

    private String bird;

    private String path;

    private Boolean approvement;

    private UUID uploaderId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBird() {
        return bird;
    }

    public void setBird(String bird) {
        this.bird = bird;
    }

    public String getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = String.valueOf(path);
    }

    public Boolean getApprovement() {
        return approvement;
    }

    public void setApprovement(Boolean approvement) {
        this.approvement = approvement;
    }

    public UUID getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(UUID uploaderId) {
        this.uploaderId = uploaderId;
    }

    public BirdImage(long id, String bird, String path, Boolean approvement, UUID uploaderId) {
        this.id = id;
        this.bird = bird;
        this.path = path;
        this.approvement = approvement;
        this.uploaderId = uploaderId;
    }
}
