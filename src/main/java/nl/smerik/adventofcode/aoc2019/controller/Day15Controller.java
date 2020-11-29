package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day15Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/15")
public class Day15Controller {

    private final Day15Service dayService;

    public Day15Controller(final Day15Service dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01() {
        return ResponseEntity.ok(dayService.getSolutionPart1());
    }
}
