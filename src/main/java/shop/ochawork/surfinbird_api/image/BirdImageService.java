package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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

    public ResponseEntity<?> addImage(MultipartFile birdImage, UUID userId) {
        Boolean approvement = false;
        String fileName = birdImage.getOriginalFilename();
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        String address = "http://" + localHost.getHostAddress() + ":8080/images/";

        if(userService.getUserById(userId).getRole() == Role.ADMIN ||
                userService.getUserById(userId).getRole() == Role.MOD){
            approvement = true;
        }
        try {
            birdImage.transferTo( new File(getImgDir(), fileName));
            birdImageDateAccessService.insertImage(
                    userId,
                    address + fileName,
                    approvement
                    );
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    public List<BirdImage> getAllImages() {
        return birdImageDateAccessService.selectAllImages();
    }
}
