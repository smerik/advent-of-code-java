package nl.smerik.adventofcode.aoc2019.model.repairdroid;

import lombok.Getter;
import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairDroid {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepairDroid.class);

    private final IntcodeComputer intcodeComputer;

    @Getter
    private Point currentPosition;

    private Direction facingDirection;

    @Getter
    enum Direction {
        NORTH(1, 0, 1),
        SOUTH(2, 0, -1),
        WEST(3, -1, 0),
        EAST(4, 1, 0);

        private final long code;
        private final int moveX;
        private final int moveY;

        Direction(final long code, final int moveX, final int moveY) {
            this.code = code;
            this.moveX = moveX;
            this.moveY = moveY;
        }

        public Direction getLeft() {
            switch (this) {
                case NORTH:
                    return Direction.WEST;
                case WEST:
                    return Direction.SOUTH;
                case SOUTH:
                    return Direction.EAST;
                case EAST:
                    return Direction.NORTH;
                default:
                    throw new UnsupportedOperationException("getLeft() not implemented for facing direction " + this);
            }
        }

        public Direction getRight() {
            switch (this) {
                case NORTH:
                    return Direction.EAST;
                case EAST:
                    return Direction.SOUTH;
                case SOUTH:
                    return Direction.WEST;
                case WEST:
                    return Direction.NORTH;
                default:
                    throw new UnsupportedOperationException("getRight() not implemented for facing direction " + this);
            }
        }
    }

    @Getter
    enum MoveResponseType {
        HIT_WALL(0, Cell.Type.WALL),
        MOVED(1, Cell.Type.SPACE),
        MOVED_AND_FOUND_OXYGEN(2, Cell.Type.OXYGEN);

        private final int code;
        private final Cell.Type type;

        MoveResponseType(final int code, final Cell.Type type) {
            this.code = code;
            this.type = type;
        }

        public static MoveResponseType valueOfCode(final Long code) {
            if (code == null) {
                throw new IllegalArgumentException("Movement response code is not allowed to be empty");
            }
            for (final MoveResponseType value : values()) {
                if (value.code == code) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Unknown movement response code " + code);
        }
    }

    public RepairDroid(final IntcodeComputer intcodeComputer) {
        this.intcodeComputer = intcodeComputer;
        this.currentPosition = new Point();
        this.facingDirection = Direction.NORTH;
    }

    public void rotateLeft() {
        setFacingDirection(facingDirection.getLeft());
    }

    public void rotateRight() {
        setFacingDirection(facingDirection.getRight());
    }

    /**
     * Moves the droid into its facing direction and will return the {@link Cell} at the directed location.
     * In case the droid hits a wall, its position will remain the same.
     *
     * @return the cell of the location the robot was facing to
     */
    public Cell moveForward() {
        return move(facingDirection);
    }

    /**
     * Moves the droid into given direction and will return the {@link Cell} at the directed location.
     * In case the droid hits a wall, its position will remain the same.
     *
     * @param direction the direction to move the droid to.
     * @return the cell of given direction
     */
    public Cell move(final Direction direction) {
        final Point point = determinePosition(direction);
        LOGGER.debug("Going to move from {} to {}...", this.currentPosition, point);
        final List<Long> output = this.intcodeComputer.run(direction.code);
        LOGGER.debug("Move result: {}", output);
        if (output.isEmpty()) {
            throw new IllegalStateException("Unexpected output on move '" + direction
                    + "' at position " + currentPosition);
        }
        final MoveResponseType response = MoveResponseType.valueOfCode(output.get(0));
        if (response == MoveResponseType.MOVED || response == MoveResponseType.MOVED_AND_FOUND_OXYGEN) {
            this.currentPosition = point;
        }
        LOGGER.debug("New position: {}", this.currentPosition);
        return new Cell(point, response.getType());
    }

    private Point determinePosition(final Direction direction) {
        final Point result = new Point(this.currentPosition);
        result.translate(direction.moveX, direction.moveY);
        return result;
    }

    private void setFacingDirection(final Direction direction) {
        LOGGER.debug("Changing facing direction from {} to {}...", this.facingDirection, direction);
        this.facingDirection = direction;
    }
}
