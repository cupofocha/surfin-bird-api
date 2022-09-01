package shop.ochawork.surfinbird_api.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(path = "/{searchWord}")
    public SearchResult search(@PathVariable("searchWord") String searchWord){
        return searchService.search(searchWord);
    }

}
