package shop.ochawork.surfinbird_api.bird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdService {
    private final BirdDataAccessService birdDataAccessService;

    @Autowired
    public BirdService(BirdDataAccessService birdDataAccessService) {
        this.birdDataAccessService = birdDataAccessService;
    }

    List<Bird> getAllBirds() {
        return birdDataAccessService.selectAllBirds();
    }

    Bird getBirdByName(String name) {
        return birdDataAccessService.selectBirdByName(name);
    }

    int addNewBird(Bird bird) {
        return birdDataAccessService.insertBird(bird);
    }
}
