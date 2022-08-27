package shop.ochawork.surfinbird_api.post;

import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.image.BirdImage;

import java.util.List;
import java.util.UUID;

public class ImagePost {
    private long id;

    private UUID posterId;

    private String text;

    private BirdImage birdImage;

    private List<Comment> comments;

    public BirdImage getBirdImage() {
        return birdImage;
    }

    public void setBirdImage(BirdImage birdImage) {
        this.birdImage = birdImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getPosterId() {
        return posterId;
    }

    public void setPosterId(UUID posterId) {
        this.posterId = posterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ImagePost(long id, UUID posterId, String text, BirdImage birdImage, List<Comment> comments) {
        this.id = id;
        this.posterId = posterId;
        this.text = text;
        this.birdImage = birdImage;
        this.comments = comments;
    }

}
