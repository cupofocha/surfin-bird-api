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

    public int addPost(ImagePost imagePost) {
        return imagePostDataAccessService.insertImagePost(imagePost.getPosterId(),
                imagePost.getText(),
                imagePost.getBirdImage().getId());
    }

    public ImagePost getPostById(long id) {
        return imagePostDataAccessService.selectPostById(id);
    }
}
