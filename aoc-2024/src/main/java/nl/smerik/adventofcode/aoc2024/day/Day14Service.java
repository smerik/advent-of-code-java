package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.bathroom.Bathroom;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day14Service {

    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    private final List<String> lines;

    public Day14Service(@Value("classpath:input/day-14.txt") Resource resource) {
        this.lines = PuzzleInputParser.parseToString(resource);
    }

    public int getSolutionPart1() {
        final Bathroom bathroom = new Bathroom(WIDTH, HEIGHT, lines);
        bathroom.move(100);
        return bathroom.calculateSafetyFactor();
    }

    public int getSolutionPart2() {
        final Bathroom bathroom = new Bathroom(WIDTH, HEIGHT, lines);
        return bathroom.determineSecondsToFindEasterEgg();
    }
}
