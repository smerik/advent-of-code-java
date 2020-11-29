package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day05Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/05")
public class Day05Controller {

    private final Day05Service day05Service;

    public Day05Controller(final Day05Service day05Service) {
        this.day05Service = day05Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Long> getSolutionPart01() {
        return ResponseEntity.ok(day05Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Long> getSolutionPart02() {
        return ResponseEntity.ok(day05Service.getSolutionPart2());
    }
}
