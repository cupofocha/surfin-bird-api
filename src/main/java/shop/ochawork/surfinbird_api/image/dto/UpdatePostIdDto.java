package shop.ochawork.surfinbird_api.image.dto;

public class UpdatePostIdDto {
    private long id;
    private long postId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public UpdatePostIdDto(long id, long postId) {
        this.id = id;
        this.postId = postId;
    }
}
