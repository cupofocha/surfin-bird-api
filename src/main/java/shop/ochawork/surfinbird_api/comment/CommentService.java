package shop.ochawork.surfinbird_api.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ochawork.surfinbird_api.comment.dto.CommentDto;
import shop.ochawork.surfinbird_api.user.UserDataAccessService;

import java.util.List;

@Service
public class CommentService {
    private final CommentDataAccessService commentDataAccessService;

    private final UserDataAccessService userDataAccessService;

    @Autowired
    public CommentService(CommentDataAccessService commentDataAccessService, UserDataAccessService userDataAccessService) {
        this.commentDataAccessService = commentDataAccessService;
        this.userDataAccessService = userDataAccessService;
    }

    public int addComment(CommentDto commentDto, String postType, long postId) {
        try {
            userDataAccessService.selectUserById(commentDto.getCommenterId());
        }
        catch (Exception e) {
            return 0;
        }
        return commentDataAccessService.insertComment(commentDto.getCommenterId(), commentDto.getText(), postType, postId);
    }

    public List<Comment> getCommentsByPostId(String postType, long postId) {
        return commentDataAccessService.selectCommentsByPostId(postType, postId);
    }

    public List<Comment> getProfileComments(String postType) {
        return commentDataAccessService.selectCommentsByType(postType);
    }
}
