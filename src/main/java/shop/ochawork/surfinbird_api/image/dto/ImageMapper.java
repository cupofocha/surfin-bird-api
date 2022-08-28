package shop.ochawork.surfinbird_api.image.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.image.BirdImage;
import shop.ochawork.surfinbird_api.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapper {
    private final UserService userService;

    @Autowired
    public ImageMapper(UserService userService) {
        this.userService = userService;
    }

    public DisplayBirdImageDto toDisplayBirdImageDto(BirdImage birdImage) {
        return new DisplayBirdImageDto(
                birdImage.getId(),
                birdImage.getBird(),
                birdImage.getPath(),
                birdImage.getApprovement(),
                userService.getUserById(birdImage.getUploaderId()).getDisplayName(),
                birdImage.getPostId()
        );
    }

    public List<DisplayBirdImageDto> toDisplayBirdImageDtoList(List<BirdImage> birdImageList) {
        List<DisplayBirdImageDto> displayBirdImageDtoList = new ArrayList<>();
        birdImageList.stream()
                .forEach(birdImage -> displayBirdImageDtoList.add(toDisplayBirdImageDto(birdImage)));
        return displayBirdImageDtoList;
    }
}
