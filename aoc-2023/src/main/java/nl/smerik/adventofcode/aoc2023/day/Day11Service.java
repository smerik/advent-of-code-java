package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.cosmic.Cosmic;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day11Service {

    private final List<String> lines;

    public Day11Service(@Value("classpath:input/day-11.txt") final Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Integer getSolutionPart1() {
        final Cosmic cosmic = new Cosmic(lines);
        cosmic.expandUniverse();
        return cosmic.sumShortestPathBetweenAllGalaxyPairs();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
