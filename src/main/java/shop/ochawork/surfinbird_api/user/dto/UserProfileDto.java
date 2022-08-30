package shop.ochawork.surfinbird_api.user.dto;

import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.image.AvatarImage;
import shop.ochawork.surfinbird_api.image.BirdImage;

import java.util.List;

public class UserProfileDto {
    private String displayName;

    private String email;

    private AvatarImage avatarImage;

    private List<BirdImage> imageList;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AvatarImage getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(AvatarImage avatarImage) {
        this.avatarImage = avatarImage;
    }

    public List<BirdImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<BirdImage> imageList) {
        this.imageList = imageList;
    }

    public UserProfileDto(String displayName, String email, AvatarImage avatarImage, List<BirdImage> imageList) {
        this.displayName = displayName;
        this.email = email;
        this.avatarImage = avatarImage;
        this.imageList = imageList;
    }
}
