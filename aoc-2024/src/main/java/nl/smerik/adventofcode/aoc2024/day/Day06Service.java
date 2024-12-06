package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.lab.Guard;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day06Service {

    private final Guard guard;

    public Day06Service(@Value("classpath:input/day-06.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        guard = new Guard(lines);
    }

    public long getSolutionPart1() {
        return guard.countDistinctPositionsVisited();
    }
}
