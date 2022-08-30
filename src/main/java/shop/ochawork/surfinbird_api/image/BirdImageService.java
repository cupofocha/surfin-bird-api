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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    public BirdImageUploadResponse addImage(MultipartFile birdImage, UUID userId) {
        Boolean approvement = false;
        String fileName = birdImage.getOriginalFilename();
        String address = "http://20.56.120.77:8080/images/";

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
                return new BirdImageUploadResponse(birdImageDateAccessService.getImageByPath(address + fileName).getId(),
                        "Image already exists!");
            }
            else return new BirdImageUploadResponse(birdImageDateAccessService.getImageByPath(address + fileName).getId(),
                    "Success");
        }
        catch (Exception e) {
            Logger.error(e);
            return new BirdImageUploadResponse(0
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
}
