package shop.ochawork.surfinbird_api.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ochawork.surfinbird_api.post.ImagePostDataAccessService;
import shop.ochawork.surfinbird_api.post.dto.PostMapper;
import shop.ochawork.surfinbird_api.user.UserDataAccessService;

@Service
public class SearchService {
    private final ImagePostDataAccessService imagePostDataAccessService;

    private final UserDataAccessService userDataAccessService;

    private final PostMapper postMapper;

    @Autowired
    public SearchService(ImagePostDataAccessService imagePostDataAccessService, UserDataAccessService userDataAccessService, PostMapper postMapper) {
        this.imagePostDataAccessService = imagePostDataAccessService;
        this.userDataAccessService = userDataAccessService;
        this.postMapper = postMapper;
    }

    public SearchResult search(String word) {
        return new SearchResult(postMapper.toSearchPostDtoList(imagePostDataAccessService.selectPostBySearch(word)),
                userDataAccessService.selectUserByKeyword(word));
    }
}
