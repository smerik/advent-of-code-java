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

    @Value("classpath:input/day-05.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final Almanac parser = AlmanacParser.parse(lines);
        return parser.findLowestLocationNumber();
    }

    public Long getSolutionPart2() {
        return null;
    }
}
