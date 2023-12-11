package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.cosmic.Cosmic;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day11Service {

    private final Cosmic cosmic;

    public Day11Service(@Value("classpath:input/day-11.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        cosmic = new Cosmic(lines);
    }

    public Long getSolutionPart1() {
        cosmic.setExpansionFactor(2);
        return cosmic.sumShortestPathBetweenAllGalaxyPairs();
    }

    public Long getSolutionPart2() {
        cosmic.setExpansionFactor(1_000_000);
        return cosmic.sumShortestPathBetweenAllGalaxyPairs();
    }
}
