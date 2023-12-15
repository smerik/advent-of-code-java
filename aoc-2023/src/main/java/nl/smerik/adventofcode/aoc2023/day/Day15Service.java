package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.lenslibrary.InitializationProcessor;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day15Service {

    private final List<String> lines;

    public Day15Service(@Value("classpath:input/day-15.txt") final Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Integer getSolutionPart1() {
        final InitializationProcessor processor = new InitializationProcessor(lines.get(0));
        return processor.sumInitializationSteps();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
