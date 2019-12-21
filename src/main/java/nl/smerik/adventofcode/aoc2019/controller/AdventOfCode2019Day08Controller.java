package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day08Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/solutions/days/08")
public class AdventOfCode2019Day08Controller {

    private final Day08Service day08Service;

    public AdventOfCode2019Day08Controller(final Day08Service day08Service) {
        this.day08Service = day08Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() throws IOException {
        // 23352 is too low
        return ResponseEntity.ok(day08Service.getSolutionPart1());
    }

    @GetMapping("/part-02")
    public ResponseEntity<String> getSolutionPart02() throws IOException {
        return ResponseEntity.ok(day08Service.getSolutionPart2());
    }
}
