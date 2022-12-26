package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.cave.ChitonCave;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day15Service {

    @Value("classpath:input/day-15.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final ChitonCave cave = new ChitonCave(lines);
        return cave.calculateTotalRisk();
    }

    public Long getSolutionPart2() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final ChitonCave cave = new ChitonCave(lines, 5);
        return cave.calculateTotalRisk();
    }
}
