package shop.ochawork.surfinbird_api.image.dto;

import java.util.UUID;

public class DisplayBirdImageDto {
    private long id;

    private String bird;

    private String path;

    private Boolean approvement;

    private String uploaderName;

    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public DisplayBirdImageDto(long id, String bird, String path, Boolean approvement, String uploaderName, long postId) {
        this.id = id;
        this.bird = bird;
        this.path = path;
        this.approvement = approvement;
        this.uploaderName = uploaderName;
        this.postId = postId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getApprovement() {
        return approvement;
    }

    public void setApprovement(Boolean approvement) {
        this.approvement = approvement;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }
}
