package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.maze.reindeer.ReindeerMaze;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day16Service {

    private final List<String> lines;

    public Day16Service(@Value("classpath:input/day-16.txt") Resource resource) {
        this.lines = PuzzleInputParser.parseToString(resource);
    }

    public int getSolutionPart1() {
        final ReindeerMaze maze = new ReindeerMaze(lines);
        return maze.findLowestScore();
    }
}
