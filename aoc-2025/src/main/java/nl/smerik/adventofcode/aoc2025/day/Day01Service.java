package nl.smerik.adventofcode.aoc2025.day;

import nl.smerik.adventofcode.aoc2025.model.safe.Safe;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    private final List<String> lines;

    public Day01Service(@Value("classpath:input/day-01.txt") Resource resource) {
        this.lines = PuzzleInputParser.parseToString(resource);
    }

    public Integer getSolutionPart1() {
        final Safe safe = new Safe(lines);
        return safe.determinePassword();
    }
}
