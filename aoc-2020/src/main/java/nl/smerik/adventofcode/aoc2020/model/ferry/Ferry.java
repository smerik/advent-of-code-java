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
    private final Point wayPoint;

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
        this(startPosition, new Point());
    }

    public Ferry(final Point startPosition, final Point wayPoint) {
        this.location = new Point(startPosition);
        this.currentDirection = Direction.EAST;
        this.wayPoint = wayPoint;
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

    public void navigateWithWaypoint(final List<String> navigationInstructions) {
        for (final String navigationInstruction : navigationInstructions) {
            followNavigationInstructionForWaypoint(navigationInstruction);
        }
    }

    private void followNavigationInstructionForWaypoint(final String instruction) {
        final Matcher matcher = NAVIGATION_INSTRUCTION_PATTERN.matcher(instruction);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid navigation instruction '" + instruction + "'");
        }

        final char action = matcher.group("action").charAt(0);
        final int value = Integer.parseInt(matcher.group("value"));
        switch (action) {
            case 'N' -> moveWaypoint(Direction.NORTH, value);
            case 'S' -> moveWaypoint(Direction.SOUTH, value);
            case 'E' -> moveWaypoint(Direction.EAST, value);
            case 'W' -> moveWaypoint(Direction.WEST, value);
            case 'L' -> rotateWaypointAroundTheShip(-1 * value);
            case 'R' -> rotateWaypointAroundTheShip(value);
            case 'F' -> moveToWaypoint(value);
            default -> throw new IllegalArgumentException("Action '" + action + "' not implemented.");
        }
    }

    private void rotateWaypointAroundTheShip(final int degrees) {
        final int distanceX = this.wayPoint.x - this.location.x;
        final int distanceY = this.wayPoint.y - this.location.y;
        switch (degrees) {
            case -90, 270 -> this.wayPoint.move(this.location.x - distanceY, this.location.y + distanceX);
            case -180, 180 -> this.wayPoint.move(this.location.x - distanceX, this.location.y - distanceY);
            case -270, 90 -> this.wayPoint.move(this.location.x + distanceY, this.location.y - distanceX);
            default -> throw new IllegalArgumentException("Degrees " + degrees + " not implemented.");
        }
    }

    private int cos(int r, int deg) {
        return (int) Math.round(r * Math.cos(Math.toRadians(deg)));
    }

    private int sin(int r, int deg) {
        return (int) Math.round(r * Math.sin(Math.toRadians(deg)));
    }

    private void moveWaypoint(final Direction direction, final int value) {
        this.wayPoint.translate(direction.moveX * value, direction.moveY * value);
    }

    private void moveToWaypoint(final int value) {
        final int moveX = this.wayPoint.x - this.location.x;
        final int moveY = this.wayPoint.y - this.location.y;
        for (int i = 0; i < value; i++) {
            this.location.translate(moveX, moveY);
            this.wayPoint.translate(moveX, moveY);
        }
    }
}
