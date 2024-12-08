package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.antenna.EasterAntennasMap;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day08Service {

    private final EasterAntennasMap antennasMap;

    public Day08Service(@Value("classpath:input/day-08.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        antennasMap = new EasterAntennasMap(lines);
    }

    public int getSolutionPart1() {
        return antennasMap.countUniqueAntinodeLocations(false);
    }

    public int getSolutionPart2() {
        return antennasMap.countUniqueAntinodeLocations(true);
    }
}
