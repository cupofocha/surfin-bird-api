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


    @PostMapping(path = "{userId}")
    public ResponseEntity<?> handleFileUpload(@RequestParam("birdImage") MultipartFile birdImage,
                                              @PathVariable("userId") UUID userId) {
        return birdImageService.addImage(birdImage, userId);
    }

    @GetMapping
    public List<BirdImage> getAllImages(){
        return birdImageService.getAllImages();
    }
}
