package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.stone.PlutonianPebbles;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day11Service {

    private final List<String> lines;

    public Day11Service(@Value("classpath:input/day-11.txt") Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public long getSolutionPart1() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(lines);
        pebbles.blink(25);
        return pebbles.countStones();
    }

    public long getSolutionPart2() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(lines);
        pebbles.blink(75);
        return pebbles.countStones();
    }
}
