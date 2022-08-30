package shop.ochawork.surfinbird_api.comment.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    private final UserService userService;

    @Autowired
    public CommentMapper(UserService userService) {
        this.userService = userService;
    }

    public List<DisplayCommentDto> toDisplayCommentDtoList(List<Comment> commentList) {
        List<DisplayCommentDto> displayCommentDtoList = new ArrayList<>();
        commentList.stream()
                .forEach(comment -> {displayCommentDtoList.add(toDisplayCommentDto(comment));});
        return displayCommentDtoList;
    }

    public DisplayCommentDto toDisplayCommentDto(Comment comment) {
        return new DisplayCommentDto(userService.getUserById(
                comment.getCommenterId()).getDisplayName(),
                comment.getText(),
                comment.getId(),
                comment.getCommenterId());
    }
}
