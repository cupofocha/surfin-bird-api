package shop.ochawork.surfinbird_api.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagePostService {
    private final ImagePostDataAccessService imagePostDataAccessService;

    @Autowired
    public ImagePostService(ImagePostDataAccessService imagePostDataAccessService) {
        this.imagePostDataAccessService = imagePostDataAccessService;
    }

    public PostResponse addPost(ImagePost imagePost) {
        return new PostResponse(imagePostDataAccessService.insertImagePost(imagePost.getPosterId(),
                imagePost.getText(),
                imagePost.getBirdImage().getId()), "Success");
    }

    public ImagePost getPostById(long id) {
        return imagePostDataAccessService.selectPostById(id);
    }

    public PostResponse updateText(long id, String text) {
        return imagePostDataAccessService.updateTextById(id, text);
    }

    public PostResponse deletePost(long id) {
        return imagePostDataAccessService.deletePostById(id);
    }
}