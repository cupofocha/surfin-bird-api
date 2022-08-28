package shop.ochawork.surfinbird_api.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImagePostService {
    private final ImagePostDataAccessService imagePostDataAccessService;

    @Autowired
    public ImagePostService(ImagePostDataAccessService imagePostDataAccessService) {
        this.imagePostDataAccessService = imagePostDataAccessService;
    }

    public PostState addPost(ImagePost imagePost) {
        return new PostState(imagePostDataAccessService.insertImagePost(imagePost.getPosterId(),
                imagePost.getText(),
                imagePost.getBirdImage().getId()), "Success");
    }

    public ImagePost getPostById(long id) {
        return imagePostDataAccessService.selectPostById(id);
    }
}
