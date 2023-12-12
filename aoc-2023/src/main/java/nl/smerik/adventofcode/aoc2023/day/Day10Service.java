package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.maze.pipe.PipeMaze;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day10Service {

    private final PipeMaze maze;

    public Day10Service(@Value("classpath:input/day-10.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        maze = new PipeMaze(lines);
    }

    public Integer getSolutionPart1() {
        return maze.determineStepCountFromStartToFarthestPoint();
    }

    public Integer getSolutionPart2() {
        return maze.countTilesEnclosedByLoop();
    }
}
