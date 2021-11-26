package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.conway.Satellite;
import nl.smerik.adventofcode.aoc2020.model.conway.Satellite4D;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day17Service {

    @Value("classpath:input/day-17.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> flatRegionOfCubes = PuzzleInputParser.parseToString(resource);
        final Satellite satellite = new Satellite(flatRegionOfCubes);
        satellite.execute(6);
        return satellite.countActiveCubes();
    }

    public Long getSolutionPart2() {
        final List<String> flatRegionOfCubes = PuzzleInputParser.parseToString(resource);
        final Satellite4D satellite = new Satellite4D(flatRegionOfCubes);
        satellite.execute(6);
        return satellite.countActiveCubes();
    }
}
