package shop.ochawork.surfinbird_api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.ochawork.surfinbird_api.image.dto.DisplayBirdImageDto;
import shop.ochawork.surfinbird_api.image.dto.ImageMapper;
import shop.ochawork.surfinbird_api.image.dto.UpdatePostIdDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("birdImages")
public class BirdImageController {
    private final BirdImageService birdImageService;

    private final ImageMapper imageMapper;

    @Autowired
    public BirdImageController(BirdImageService birdImageService, ImageMapper imageMapper) {
        this.birdImageService = birdImageService;
        this.imageMapper = imageMapper;
    }


    @PostMapping(path = "upload-image/{userId}")
    public BirdImageResponse handleFileUpload(MultipartFile birdImage,
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
    public List<DisplayBirdImageDto> getAllImages(){
        return imageMapper.toDisplayBirdImageDtoList(birdImageService.getAllImages());
    }

    @PostMapping(path = "update-image-postid")
    public int updateImagePostId(@RequestBody UpdatePostIdDto updatePostIdDto){
        return birdImageService.updateImagePostId(updatePostIdDto.getId(), updatePostIdDto.getPostId());
    }

    @DeleteMapping(path = "{imageId}")
    public BirdImageResponse deleteImageById(@PathVariable ("ImageId") long id) {
        return birdImageService.deleteImageById(id);
    }
}
