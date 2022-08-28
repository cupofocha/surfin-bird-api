package shop.ochawork.surfinbird_api.comment.dto;

public class DisplayCommentDto {
    private String commenterName;

    private String text;

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

    public DisplayCommentDto(String commenterName, String text) {
        this.commenterName = commenterName;
        this.text = text;
    }
}
