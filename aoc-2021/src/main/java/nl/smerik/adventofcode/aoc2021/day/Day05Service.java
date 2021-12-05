package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.fissure.HydrothermalVent;
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
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return new HydrothermalVent(input, false).countOverlappingPoints();
    }

    public Long getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return new HydrothermalVent(input, true).countOverlappingPoints();
    }
}
