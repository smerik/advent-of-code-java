package nl.smerik.adventofcode.aoc2019.model;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Wire {

    private List<Point> coordinates;

    public Wire(final String wireDirections) {
        coordinates = new ArrayList<>(Collections.singletonList(new Point()));

        for (String direction : wireDirections.split(",")) {
            putWire(direction);
        }
    }

    private void putWire(final String path) {
        String direction = path.substring(0, 1);
        int steps = Integer.parseInt(path.substring(1));
//        LOGGER.info("Move - direction:{} - steps:{}", direction, steps);

        for (int i = 0; i < steps; i++) {
            final Point currentPos = (Point) coordinates.get(coordinates.size() - 1).clone();
            switch (direction) {
                case "L":
                    currentPos.move(currentPos.x - 1, currentPos.y);
                    break;
                case "R":
                    currentPos.move(currentPos.x + 1, currentPos.y);
                    break;
                case "U":
                    currentPos.move(currentPos.x, currentPos.y + 1);
                    break;
                case "D":
                    currentPos.move(currentPos.x, currentPos.y - 1);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown direction '" + direction + "'.");
            }
            coordinates.add(currentPos);
        }
    }

    public int getNumberOfStepsToPoint(final Point point) {
        int result = 0;
        for (final Point coordinate : coordinates) {
            if (coordinate.equals(point)) {
                break;
            }
            result++;
        }
        return result;
    }
}
