package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tinylog.Logger;
import shop.ochawork.surfinbird_api.user.Role;
import shop.ochawork.surfinbird_api.user.UserService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BirdImageService {
    UserService userService;

    BirdImageDateAccessService birdImageDateAccessService;

    @Autowired
    public BirdImageService(BirdImageDateAccessService birdImageDateAccessService, UserService userService) {
        this.birdImageDateAccessService = birdImageDateAccessService;
        this.userService = userService;
    }

    private static File getImgDir() throws IOException {
        final File homeDir = new File(System.getProperty("user.home"));
        return new File(homeDir, "/SurfinBird/Images");
    }

    public BirdImage getImageById(long id) {
        return birdImageDateAccessService.selectImageById(id);
    }

    public BirdImageResponse addImage(MultipartFile birdImage, UUID userId) {
        Boolean approvement = false;
        String fileName = birdImage.getOriginalFilename();
        String address = "http://api.ochawork.shop/images/";

        if(userService.getUserById(userId).getRole() == Role.ADMIN ||
                userService.getUserById(userId).getRole() == Role.MOD){
            approvement = true;
        }
        try {
            birdImage.transferTo( new File(getImgDir(), fileName));
            if(birdImageDateAccessService.insertImage(
                    userId,
                    address + fileName,
                    approvement,
                    0
                    ) == 0){
                return new BirdImageResponse(birdImageDateAccessService.getImageByPath(address + fileName).getId(),
                        "Image already exists!");
            }
            else return new BirdImageResponse(birdImageDateAccessService.getImageByPath(address + fileName).getId(),
                    "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new BirdImageResponse(0
                    ,HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }

    public int updateImagePostId(long id, long postId) {
        return birdImageDateAccessService.updateImagePostIdById(id, postId);
    }

    public List<BirdImage> getAllImages() {
        return birdImageDateAccessService.selectAllImages();
    }

    public List<BirdImage> getImagesByUploaderId(UUID userId) { return birdImageDateAccessService.selectBirdImagesByUploaderId(userId); }

    public BirdImageResponse deleteImageById(long id) {
        return birdImageDateAccessService.deleteImageById(id);
    }

    public BirdImageResponse deleteImageByPostId(long postId) {
        return birdImageDateAccessService.deleteImageByPostId(postId);
    }
}

