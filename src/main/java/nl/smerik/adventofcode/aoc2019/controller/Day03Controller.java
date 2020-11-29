package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day03Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/03")
public class Day03Controller {

    private final Day03Service day03Service;

    public Day03Controller(final Day03Service day03Service) {
        this.day03Service = day03Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() {
        return ResponseEntity.ok(day03Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() {
        return ResponseEntity.ok(day03Service.getSolutionPart2());
    }
}
