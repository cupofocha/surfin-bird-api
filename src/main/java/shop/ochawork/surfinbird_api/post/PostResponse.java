package shop.ochawork.surfinbird_api.post;

public class PostResponse {
    private long id;

    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PostResponse(long id, String state) {
        this.id = id;
        this.state = state;
    }
}
