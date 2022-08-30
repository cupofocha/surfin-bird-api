package shop.ochawork.surfinbird_api.image;

import java.util.UUID;

public class AvatarImage {
    private long id;

    private UUID userId;

    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AvatarImage(long id, UUID userId, String path) {
        this.id = id;
        this.userId = userId;
        this.path = path;
    }
}
