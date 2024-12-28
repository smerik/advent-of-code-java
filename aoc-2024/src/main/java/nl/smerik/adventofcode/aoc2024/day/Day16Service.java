package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.maze.reindeer.ReindeerMaze;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day16Service {

    private final ReindeerMaze maze;

    public Day16Service(@Value("classpath:input/day-16.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        maze = new ReindeerMaze(lines);
    }

    public int getSolutionPart1() {
        return maze.findLowestScore();
    }

    public int getSolutionPart2() {
        return maze.countBestPathTiles();
    }
}
