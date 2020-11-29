package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day04Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/04")
public class Day04Controller {

    private final Day04Service day04Service;

    public Day04Controller(final Day04Service day04Service) {
        this.day04Service = day04Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01(@RequestParam final int rangeStart,
                                                     @RequestParam final int rangeEnd) {
        // answer: 1675
        return ResponseEntity.ok(day04Service.getSolutionPart1(rangeStart, rangeEnd));
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02(@RequestParam final int rangeStart,
                                                     @RequestParam final int rangeEnd) {
        // answer: 1193; That's not the right answer; your answer is too high.
        // answer: 1142
        return ResponseEntity.ok(day04Service.getSolutionPart2(rangeStart, rangeEnd));
    }
}
