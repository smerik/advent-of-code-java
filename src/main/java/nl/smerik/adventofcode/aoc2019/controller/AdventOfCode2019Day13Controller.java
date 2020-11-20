package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day13Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/13")
public class AdventOfCode2019Day13Controller {

    private final Day13Service dayService;

    public AdventOfCode2019Day13Controller(final Day13Service dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Long> getSolutionPart01() {
        return ResponseEntity.ok(dayService.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Long> getSolutionPart02() {
        return ResponseEntity.ok(dayService.getSolutionPart2());
    }
}
