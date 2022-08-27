package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("birdImages")
public class BirdImageController {
    private final BirdImageService birdImageService;

    @Autowired
    public BirdImageController(BirdImageService birdImageService) {
        this.birdImageService = birdImageService;
    }


    @PostMapping(path = "upload-image/{userId}")
    public ResponseEntity<?> handleFileUpload(@RequestParam("birdImage") MultipartFile birdImage,
                                              @PathVariable("userId") UUID userId) {
        return birdImageService.addImage(birdImage, userId);
    }

    @PostMapping(path = "upload-images/{userId}")
    public ResponseEntity<?> handleMultipleFileUpload(@RequestParam("birdImages") MultipartFile[] birdImages,
                                              @PathVariable("userId") UUID userId) {
        for(MultipartFile birdImage : birdImages) {
            birdImageService.addImage(birdImage, userId);
        }

        return ResponseEntity.ok("Files uploaded successfully.");
    }

    @GetMapping
    public List<BirdImage> getAllImages(){
        return birdImageService.getAllImages();
    }
}
