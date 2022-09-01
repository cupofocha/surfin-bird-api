package shop.ochawork.surfinbird_api.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.comment.CommentService;
import shop.ochawork.surfinbird_api.image.BirdImageService;
import shop.ochawork.surfinbird_api.post.dto.ImagePostDto;
import shop.ochawork.surfinbird_api.post.dto.PostMapper;
import shop.ochawork.surfinbird_api.post.dto.SearchPostDto;

import java.util.UUID;

@RestController
@RequestMapping("post")
public class ImagePostController {
    private final ImagePostService imagePostService;
    private final PostMapper postMapper;

    private final CommentService commentService;

    private final BirdImageService birdImageService;

    @Autowired
    public ImagePostController(ImagePostService imagePostService, PostMapper postMapper, CommentService commentService, BirdImageService birdImageService) {
        this.imagePostService = imagePostService;
        this.postMapper = postMapper;
        this.commentService = commentService;
        this.birdImageService = birdImageService;
    }

    @PostMapping(path = "/image/{userId}")
    public PostResponse addPost(@PathVariable("userId") UUID userId, @RequestBody ImagePostDto imagePostDto){
        System.out.println(imagePostDto);
        return imagePostService.addPost(postMapper.toImagePost(imagePostDto, userId));
    }

    @GetMapping(path = "/image/{postId}")
    public ImagePost getPost(@PathVariable("postId") long postId){
        return imagePostService.getPostById(postId);
    }

    @PutMapping(path = "/image/{postId}")
    public PostResponse editPostText(@PathVariable("postId") long id, @RequestBody String text){
        return imagePostService.updateText(id, text);
    }

    @DeleteMapping(path = "/image/{postId}")
    public PostResponse deletePost(@PathVariable("postId") long id){
        if(birdImageService.deleteImageByPostId(id).getState().equals("Success")){
            commentService.deleteCommentByPostId(id);
            return imagePostService.deletePost(id);
        }
        else return new PostResponse(id, birdImageService.deleteImageByPostId(id).getState());
    }
}