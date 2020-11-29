package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day11Service;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/11")
public class Day11Controller {

    private final Day11Service dayService;

    public Day11Controller(final Day11Service dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Long> getSolutionPart01() {
        return ResponseEntity.ok(dayService.getSolutionPart1());
    }

    @GetMapping(value = "/part-02", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSolutionPart02() {
        return ResponseEntity.ok(dayService.getSolutionPart2());
    }
}
