package nl.smerik.adventofcode.aoc2019.controller;

import nl.smerik.adventofcode.aoc2019.day.Day05Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/solutions/days/05")
public class AdventOfCode2019Day05Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdventOfCode2019Day05Controller.class);

    private final Day05Service day05Service;

    public AdventOfCode2019Day05Controller(final Day05Service day05Service) {
        this.day05Service = day05Service;
    }

    @GetMapping("/part-01")
    public ResponseEntity<Integer> getSolutionPart01(@RequestParam final int instruction) {
        LOGGER.info("instruction:{}", instruction);
        final int[] solutionPart1 = day05Service.getSolutionPart1(instruction);
        LOGGER.info("result:{}", solutionPart1);
        return ResponseEntity.ok(0);
    }

    @GetMapping("/part-02")
    public ResponseEntity<Integer> getSolutionPart02() {
        return ResponseEntity.ok(day05Service.getSolutionPart2());
    }
}
