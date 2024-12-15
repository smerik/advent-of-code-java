package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.warehouse.Warehouse;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day15Service {

    private final List<String> lines;

    public Day15Service(@Value("classpath:input/day-15.txt") Resource resource) {
        this.lines = PuzzleInputParser.parseToString(resource);
    }

    public int getSolutionPart1() {
        final Warehouse warehouse = new Warehouse(lines);
        warehouse.moveRobot();
        return warehouse.sumGPSCoordinates();
    }
}
