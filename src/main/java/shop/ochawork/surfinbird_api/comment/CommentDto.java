package shop.ochawork.surfinbird_api.comment;

import java.util.UUID;

public class CommentDto {
    private UUID commenterId;

    private String text;

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

    public CommentDto(UUID commenterId, String text) {
        this.commenterId = commenterId;
        this.text = text;
    }
}
