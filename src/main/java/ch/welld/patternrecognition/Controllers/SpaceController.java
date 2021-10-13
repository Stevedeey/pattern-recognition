package ch.welld.patternrecognition.Controllers;

import ch.welld.patternrecognition.Models.Point;
import ch.welld.patternrecognition.Services.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping("/point")
    public ResponseEntity<?> addPoint(@RequestBody Point point) {
        log.info("adding point to the space");
        return new ResponseEntity<>(spaceService.addPoint(point), HttpStatus.CREATED);
    }

    @DeleteMapping("/space")
    public ResponseEntity<?> deleteSpace() {
        log.info("Clearing space: All the previously added points are being removed");
        spaceService.clearAll();
        return new ResponseEntity<>("All points on space has been cleared", HttpStatus.ACCEPTED);
    }


    @GetMapping("/space")
    public ResponseEntity<?> getAllPoints() {
        log.info("Fetching all points previously added to the space");
        return new ResponseEntity<>(spaceService.getAllPoints(), HttpStatus.OK);
    }


    @GetMapping("/lines/{n}")
    public ResponseEntity<?> getAllPointsFromEachLineByNumberOfPoints(@PathVariable(name = "n") int quantifyOfPoints) {
        log.info("Fetching all lines previously added to the space which contains {} or more points", quantifyOfPoints);
        return new ResponseEntity<>(spaceService.getAllPointsFromLinesByNumberOfPoints(quantifyOfPoints), HttpStatus.OK);
    }


    @GetMapping("/lines")
    public ResponseEntity<?> getAllLines() {
        log.info("Fetching all lines in the space");
        return new ResponseEntity<>(spaceService.getAllPointsFromLinesByNumberOfPoints(1), HttpStatus.OK);
    }
}