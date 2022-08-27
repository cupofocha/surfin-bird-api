package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.post.dto.ImagePostDto;

import java.util.UUID;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/{postType}/{postId}")
    public int addComment(@PathVariable("postType") String postType, @PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto, postType, postId);
    }
}
