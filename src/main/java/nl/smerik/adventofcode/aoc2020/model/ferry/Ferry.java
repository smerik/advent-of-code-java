package nl.smerik.adventofcode.aoc2020.model.ferry;

import lombok.Getter;

import java.awt.Point;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Ferry {

    private static final Pattern NAVIGATION_INSTRUCTION_PATTERN = Pattern.compile("(?<action>[NSEWLRF])(?<value>\\d+)");

    private final Point location;

    private Direction currentDirection;

    private enum Direction {
        NORTH(0, 0, 1),
        EAST(90, 1, 0),
        SOUTH(180, 0, -1),
        WEST(270, -1, 0);

        private final int degrees;
        private final int moveX;
        private final int moveY;

        Direction(final int degrees, final int moveX, final int moveY) {
            this.degrees = degrees;
            this.moveX = moveX;
            this.moveY = moveY;
        }

        public Direction turn(final int degrees) {
            int result = this.degrees + degrees;
            if (result < 0) {
                result += 360;
            } else if (result >= 360) {
                result -= 360;
            }
            return valueOfDegrees(result);
        }

        public static Direction valueOfDegrees(final int degrees) {
            for (final Direction direction : values()) {
                if (direction.degrees == degrees) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Cannot return direction for degrees value " + degrees);
        }
    }

    public Ferry(final Point startPosition) {
        this.location = new Point(startPosition);
        this.currentDirection = Direction.EAST;
    }

    public int calculateManhattanDistance(final Point location) {
        return Math.abs(this.location.x - location.x) + Math.abs(this.location.y - location.y);
    }

    public void navigate(final List<String> navigationInstructions) {
        for (final String navigationInstruction : navigationInstructions) {
            followNavigationInstruction(navigationInstruction);
        }
    }

    private void followNavigationInstruction(final String instruction) {
        final Matcher matcher = NAVIGATION_INSTRUCTION_PATTERN.matcher(instruction);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid navigation instruction '" + instruction + "'");
        }

        final char action = matcher.group("action").charAt(0);
        final int value = Integer.parseInt(matcher.group("value"));
        switch (action) {
            case 'N' -> move(Direction.NORTH, value);
            case 'S' -> move(Direction.SOUTH, value);
            case 'E' -> move(Direction.EAST, value);
            case 'W' -> move(Direction.WEST, value);
            case 'L' -> turn(-1 * value);
            case 'R' -> turn(value);
            case 'F' -> move(this.currentDirection, value);
            default -> throw new IllegalArgumentException("Action '" + action + "' not implemented.");
        }
    }

    private void turn(final int degrees) {
        this.currentDirection = this.currentDirection.turn(degrees);
    }

    private void move(final Direction direction, final int value) {
        this.location.translate(direction.moveX * value, direction.moveY * value);
    }
}
