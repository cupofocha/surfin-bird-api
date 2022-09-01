package shop.ochawork.surfinbird_api.search;

import shop.ochawork.surfinbird_api.post.ImagePost;
import shop.ochawork.surfinbird_api.post.dto.SearchPostDto;
import shop.ochawork.surfinbird_api.user.User;
import shop.ochawork.surfinbird_api.user.dto.UserInfoDto;

import java.util.List;

public class SearchResult {
    private List<SearchPostDto> SearchPostDtoList;

    private List<UserInfoDto> userInfoDtoList;

    public List<SearchPostDto> getSearchPostDtoList() {
        return SearchPostDtoList;
    }

    public void setSearchPostDtoList(List<SearchPostDto> searchPostDtoList) {
        SearchPostDtoList = searchPostDtoList;
    }

    public List<UserInfoDto> getUserInfoDtoList() {
        return userInfoDtoList;
    }

    public void setUserInfoDtoList(List<UserInfoDto> userInfoDtoList) {
        this.userInfoDtoList = userInfoDtoList;
    }

    public SearchResult(List<SearchPostDto> searchPostDtoList, List<UserInfoDto> userInfoDtoList) {
        SearchPostDtoList = searchPostDtoList;
        this.userInfoDtoList = userInfoDtoList;
    }
}
