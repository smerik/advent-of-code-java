package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.parabolicreflector.ParabolicReflector;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day14Service {

    private final List<String> lines;

    public Day14Service(@Value("classpath:input/day-14.txt") final Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Integer getSolutionPart1() {
        final ParabolicReflector reflector = new ParabolicReflector(lines);
        reflector.tiltLever();
        return reflector.sumTotalLoad();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
