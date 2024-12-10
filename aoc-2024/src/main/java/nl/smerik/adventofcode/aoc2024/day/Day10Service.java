package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.hiking.TopographicMap;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day10Service {

    private final TopographicMap map;

    public Day10Service(@Value("classpath:input/day-10.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        map = new TopographicMap(lines);
    }

    public int getSolutionPart1() {
        return map.sumTrailheadsScore();
    }
}
