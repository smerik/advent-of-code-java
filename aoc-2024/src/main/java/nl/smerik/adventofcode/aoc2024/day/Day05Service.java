package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.printer.SafetyManual;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day05Service {

    private final SafetyManual manual;

    public Day05Service(@Value("classpath:input/day-05.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        manual = new SafetyManual(lines);
    }

    public long getSolutionPart1() {
        return manual.sumMiddlePagesOfCorrectlyOrderedUpdates();
    }

    public long getSolutionPart2() {
        return manual.sumMiddlePagesOfFixedIncorrectlyOrderedUpdates();
    }
}
