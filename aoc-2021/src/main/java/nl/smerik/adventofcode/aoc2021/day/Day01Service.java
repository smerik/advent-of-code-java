package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.SonarSweep;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        return initSonarSweep().countIncreasingDepthMeasurements();
    }

    public Integer getSolutionPart2() {
        return initSonarSweep().countIncreasingDepthSlidingWindowMeasurements();
    }

    private SonarSweep initSonarSweep() {
        final List<Integer> measurements = PuzzleInputParser.parseToInt(resource);
        return new SonarSweep(measurements);
    }
}
