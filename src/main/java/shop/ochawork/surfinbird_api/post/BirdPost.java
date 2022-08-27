package shop.ochawork.surfinbird_api.post;

import shop.ochawork.surfinbird_api.comment.Comment;
import shop.ochawork.surfinbird_api.bird.Bird;

import java.util.List;
import java.util.UUID;

public class BirdPost {
    private long id;

    private UUID posterId;

    private Bird bird;

    private List<Comment> comments;
}
