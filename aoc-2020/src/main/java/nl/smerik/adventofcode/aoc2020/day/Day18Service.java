package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.service.math.MathService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day18Service {

    private final MathService mathService;

    @Value("classpath:input/day-18.txt")
    private Resource resource;

    public Day18Service(final MathService mathService) {
        this.mathService = mathService;
    }

    public Long getSolutionPart1() {
        final List<String> expressions = PuzzleInputParser.parseToString(resource);
        return mathService.sumEvaluation(expressions, false);
    }

    public Long getSolutionPart2() {
        final List<String> expressions = PuzzleInputParser.parseToString(resource);
        return mathService.sumEvaluation(expressions, true);
    }
}
