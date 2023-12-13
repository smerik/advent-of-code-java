package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.mirrorvalley.ValleyOfMirrors;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day13Service {

    private final List<String> lines;

    public Day13Service(@Value("classpath:input/day-13.txt") final Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Integer getSolutionPart1() {
        final ValleyOfMirrors valley = new ValleyOfMirrors(lines);
        return valley.sumAllPatternNotes();
    }

    public Long getSolutionPart2() {
        return null;
    }
}
