package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland.HauntedWastelandMap;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class Day08Service {

    @Value("classpath:input/day-08.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final HauntedWastelandMap map = new HauntedWastelandMap(lines);
        return map.determineStepCountToReachEndpoint();
    }

    public BigInteger getSolutionPart2() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final HauntedWastelandMap map = new HauntedWastelandMap(lines);
        return map.determineStepCountToReachEndpointAsAGhost();
    }
}
