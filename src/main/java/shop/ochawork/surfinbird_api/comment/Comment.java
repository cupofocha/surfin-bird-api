package shop.ochawork.surfinbird_api.comment;

import java.util.UUID;

public class Comment {
    private long id;

    private UUID commenterId;

    private String text;

    public Comment(long id, UUID commenterId, String text) {
        this.id = id;
        this.commenterId = commenterId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(UUID commenterId) {
        this.commenterId = commenterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
