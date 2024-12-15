package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.bathroom.Bathroom;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day14Service {

    private final Bathroom bathroom;

    public Day14Service(@Value("classpath:input/day-14.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        bathroom = new Bathroom(101, 103, lines);
    }

    public int getSolutionPart1() {
        bathroom.move(100);
        return bathroom.calculateSafetyFactor();
    }
}
