package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day06Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/06")
public class Day06Controller {

    private final Day06Service day06Service;

    public Day06Controller(final Day06Service day06Service) {
        this.day06Service = day06Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() {
        // 23352 is too low
        return ResponseEntity.ok(day06Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() {
        return ResponseEntity.ok(day06Service.getSolutionPart2());
    }
}
