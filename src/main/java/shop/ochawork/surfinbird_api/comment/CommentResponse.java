package shop.ochawork.surfinbird_api.comment;

public class CommentResponse {
    long commentId;

    String state;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CommentResponse(long commentId, String state) {
        this.commentId = commentId;
        this.state = state;
    }
}
