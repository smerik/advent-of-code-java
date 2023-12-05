package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.almanac.Almanac;
import nl.smerik.adventofcode.aoc2023.model.almanac.AlmanacParser;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day05Service {

    private final Almanac parser;

    public Day05Service(@Value("classpath:input/day-05.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        parser = AlmanacParser.parse(lines);
    }

    public Long getSolutionPart1() {
        return parser.findLowestLocationNumber();
    }

    public Long getSolutionPart2() {
        return parser.findLowestLocationNumberForARangeOfSeeds();
    }
}
