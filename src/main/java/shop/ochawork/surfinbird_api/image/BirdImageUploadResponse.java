package shop.ochawork.surfinbird_api.image;

public class BirdImageUploadResponse {
    private long imageId;

    private String state;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BirdImageUploadResponse(long imageId, String state) {
        this.imageId = imageId;
        this.state = state;
    }
}
