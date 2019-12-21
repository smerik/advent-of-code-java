package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day12Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/solutions/days/12")
public class AdventOfCode2019Day12Controller {

    private final Day12Service day12Service;

    public AdventOfCode2019Day12Controller(final Day12Service day12Service) {
        this.day12Service = day12Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() throws IOException {
        return ResponseEntity.ok(day12Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() throws IOException {
        return ResponseEntity.ok(day12Service.getSolutionPart2());
    }
}
