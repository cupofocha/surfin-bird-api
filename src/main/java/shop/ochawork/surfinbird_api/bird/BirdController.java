package shop.ochawork.surfinbird_api.bird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("bird")
public class BirdController {
    private final BirdService birdService;

    @Autowired
    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping
    public List<Bird> getAllBirds() {
        return birdService.getAllBirds();
    }

    @GetMapping(path = "{name}")
    public Bird getBirdByName( @PathVariable("name") String name) {
        return birdService.getBirdByName(name);
    }

    @PostMapping
    public int addNewBird(@RequestBody @Valid Bird bird) {
        return birdService.addNewBird(bird);
    }
}
