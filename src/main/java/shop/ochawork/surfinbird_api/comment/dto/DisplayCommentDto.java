package shop.ochawork.surfinbird_api.comment.dto;

import java.util.UUID;

public class DisplayCommentDto {
    private String commenterName;

    private String text;

    private long id;

    private UUID commenterId;

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

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DisplayCommentDto(String commenterName, String text, long id, UUID commenterId) {
        this.commenterName = commenterName;
        this.text = text;
        this.id = id;
        this.commenterId = commenterId;
    }
}
