package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.ferry.Ferry;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class Day12Service {

    @Value("classpath:input/day-12.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> navigationInstructions = PuzzleInputParser.parseToString(resource);
        final Point startPosition = new Point();
        final Ferry ferry = new Ferry(startPosition);
        ferry.navigate(navigationInstructions);
        return ferry.calculateManhattanDistance(startPosition);
    }

    public Integer getSolutionPart2() {
        final List<String> navigationInstructions = PuzzleInputParser.parseToString(resource);
        final Point startPosition = new Point();
        final Point waypoint = new Point(10, 1);
        final Ferry ferry = new Ferry(startPosition, waypoint);
        ferry.navigateWithWaypoint(navigationInstructions);
        return ferry.calculateManhattanDistance(startPosition);
    }
}
