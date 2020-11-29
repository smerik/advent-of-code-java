package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day10Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/solutions/days/10")
public class Day10Controller {

    private final Day10Service day10Service;

    public Day10Controller(final Day10Service day10Service) {
        this.day10Service = day10Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() throws IOException {
        return ResponseEntity.ok(day10Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() throws IOException {
        return ResponseEntity.ok(day10Service.getSolutionPart2());
    }
}
