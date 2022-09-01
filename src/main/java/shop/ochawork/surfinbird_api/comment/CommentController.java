package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ochawork.surfinbird_api.comment.dto.CommentDto;
import shop.ochawork.surfinbird_api.comment.dto.CommentMapper;
import shop.ochawork.surfinbird_api.comment.dto.DisplayCommentDto;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentMapper commentMapper;

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentMapper commentMapper, CommentService commentService) {
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @PostMapping(path = "/{postType}/{postId}")
    public int addComment(@PathVariable("postType") String postType, @PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto, postType, postId);
    }

    @PostMapping(path = "/{postType}")
    public int addProfileComment(@PathVariable("postType") String postType, @RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto, postType, 114514);
    }

    @GetMapping(path = "/{postType}/{postId}")
    public List<DisplayCommentDto> getComments(@PathVariable("postType") String postType, @PathVariable("postId") long postId){
        return commentMapper.toDisplayCommentDtoList(commentService.getCommentsByPostId(postType, postId));
    }

    @DeleteMapping(path = "/{commentId}")
    public CommentResponse deleteComments(@PathVariable("commentId") long id) {
        return commentService.deleteCommentById(id);
    }
}
