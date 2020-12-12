package nl.smerik.adventofcode.aoc2020.service.ferry;

import nl.smerik.adventofcode.aoc2020.model.ferry.Ferry;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.List;

@Service
public class FerryService {

    public int calculateManhattanDistance(final Point startPosition, final List<String> navigationInstructions) {
        final Ferry ferry = new Ferry(startPosition);
        ferry.navigate(navigationInstructions);
        final Point location = ferry.getLocation();
        return Math.abs(location.x) + Math.abs(location.y);
    }
}
