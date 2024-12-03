package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.computer.Calculator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    private final Calculator calculator;

    public Day03Service(@Value("classpath:input/day-03.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        calculator = new Calculator(lines);
    }

    public Long getSolutionPart1() {
        return calculator.sumAllMultiplications();
    }
}
