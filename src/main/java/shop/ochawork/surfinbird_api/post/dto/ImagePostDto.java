package shop.ochawork.surfinbird_api.post.dto;

public class ImagePostDto {
    private String text;
    private long birdImageId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getBirdImageId() {
        return birdImageId;
    }

    public void setBirdImageId(long birdImageId) {
        this.birdImageId = birdImageId;
    }

    public ImagePostDto(String text, long birdImageId) {
        this.text = text;
        this.birdImageId = birdImageId;
    }
}
