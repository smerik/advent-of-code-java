package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.cave.CaveMap;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day09Service {

    @Value("classpath:input/day-09.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final CaveMap caveMap = new CaveMap(input);
        return caveMap.sumRiskLevels();
    }

    public Integer getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final CaveMap caveMap = new CaveMap(input);
        return caveMap.multiplyThreeLargestBasins();
    }
}
