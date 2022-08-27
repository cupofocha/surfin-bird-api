package shop.ochawork.surfinbird_api.post.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.image.BirdImageService;
import shop.ochawork.surfinbird_api.post.ImagePost;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class PostMapper {
    private final BirdImageService birdImageService;

    @Autowired
    public PostMapper(BirdImageService birdImageService) {
        this.birdImageService = birdImageService;
    }

    public ImagePost toImagePost(ImagePostDto imagePostDto, UUID posterId) {
        return new ImagePost(0, posterId, imagePostDto.getText(), birdImageService.getImageById(imagePostDto.getBirdImageId()), new ArrayList<>());
    }
}
