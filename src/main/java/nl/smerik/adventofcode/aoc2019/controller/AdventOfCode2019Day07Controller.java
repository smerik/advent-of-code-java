package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day07Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/07")
public class AdventOfCode2019Day07Controller {

    private final Day07Service day07Service;

    public AdventOfCode2019Day07Controller(final Day07Service day07Service) {
        this.day07Service = day07Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() {
        return ResponseEntity.ok(day07Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() {
        return ResponseEntity.ok(day07Service.getSolutionPart2());
    }
}
