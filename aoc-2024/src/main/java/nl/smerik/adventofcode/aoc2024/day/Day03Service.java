package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.computer.Calculator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    private final List<String> lines;

    public Day03Service(@Value("classpath:input/day-03.txt") Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Long getSolutionPart1() {
        final Calculator calculator = new Calculator(lines, false);
        return calculator.sumAllMultiplications();
    }

    public Long getSolutionPart2() {
        final Calculator calculator = new Calculator(lines, true);
        return calculator.sumAllMultiplications();
    }
}
