package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day01Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/01")
public class Day01Controller {

    private final Day01Service day01Service;

    public Day01Controller(final Day01Service day01Service) {
        this.day01Service = day01Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Long> getSolutionPart01() {
        return ResponseEntity.ok(day01Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Long> getSolutionPart02() {
        return ResponseEntity.ok(day01Service.getSolutionPart2());
    }
}
