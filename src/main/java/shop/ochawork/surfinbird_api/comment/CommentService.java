package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentDataAccessService commentDataAccessService;

    @Autowired
    public CommentService(CommentDataAccessService commentDataAccessService) {
        this.commentDataAccessService = commentDataAccessService;
    }

    public int addComment(CommentDto commentDto, String postType, long postId) {
        return commentDataAccessService.insertComment(commentDto.getCommenterId(), commentDto.getText(), postType, postId);
    }
}
