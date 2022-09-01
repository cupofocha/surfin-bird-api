package shop.ochawork.surfinbird_api.post.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.image.BirdImageService;
import shop.ochawork.surfinbird_api.post.ImagePost;
import shop.ochawork.surfinbird_api.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PostMapper {
    private final BirdImageService birdImageService;

    private final UserService userService;

    @Autowired
    public PostMapper(BirdImageService birdImageService, UserService userService) {
        this.birdImageService = birdImageService;
        this.userService = userService;
    }

    public ImagePost toImagePost(ImagePostDto imagePostDto, UUID posterId) {
        return new ImagePost(0, posterId, imagePostDto.getText(), birdImageService.getImageById(imagePostDto.getBirdImageId()), new ArrayList<>());
    }

    public List<SearchPostDto> toSearchPostDtoList(List<ImagePost> imagePostList) {
        List<SearchPostDto> searchPostDtoList = new ArrayList<>();
        imagePostList.stream().forEach( imagePost -> {
            searchPostDtoList.add(
                    new SearchPostDto(
                    imagePost.getId(),
                    imagePost.getPosterId(),
                    imagePost.getText(),
                    imagePost.getBirdImage(),
                    imagePost.getComments(),
                    userService.getUserById(imagePost.getPosterId()).getDisplayName()
                )
            );
        });
        return searchPostDtoList;
    }
}
