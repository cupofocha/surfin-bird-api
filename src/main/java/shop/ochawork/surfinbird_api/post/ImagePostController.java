package shop.ochawork.surfinbird_api.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.post.dto.ImagePostDto;
import shop.ochawork.surfinbird_api.post.dto.PostMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("post")
public class ImagePostController {
    private final ImagePostService imagePostService;
    private final PostMapper postMapper;

    @Autowired
    public ImagePostController(ImagePostService imagePostService, PostMapper postMapper) {
        this.imagePostService = imagePostService;
        this.postMapper = postMapper;
    }

    @PostMapping(path = "/image/{userId}")
    public PostState addPost(@PathVariable("userId") UUID userId, @RequestBody ImagePostDto imagePostDto){
        System.out.println(imagePostDto);
        return imagePostService.addPost(postMapper.toImagePost(imagePostDto, userId));
    }

    @GetMapping(path = "/image/{postId}")
    public ImagePost getPost(@PathVariable("postId") long postId){
        return imagePostService.getPostById(postId);
    }
}
