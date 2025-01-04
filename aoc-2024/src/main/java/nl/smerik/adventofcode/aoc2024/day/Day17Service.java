package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.computer.chronospatial.ChronospatialComputer;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day17Service {

    private final ChronospatialComputer computer;

    public Day17Service(@Value("classpath:input/day-17.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        computer = new ChronospatialComputer(lines);
    }

    public String getSolutionPart1() {
        return computer.runProgram();
    }
}
