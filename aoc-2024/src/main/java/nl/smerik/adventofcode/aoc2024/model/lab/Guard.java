package nl.smerik.adventofcode.aoc2024.model.lab;

import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Guard {

    private final char[][] grid;
    private final List<Point> visitedLocations;
    private final Map<Direction, Set<Point>> visitedLocationsByDirection;
    private Point currentLocation;
    private Direction facingDirection;

    @Getter
    enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        private final int moveX;
        private final int moveY;

        Direction(final int moveX, final int moveY) {
            this.moveX = moveX;
            this.moveY = moveY;
        }

        public Direction getRight() {
            return switch (this) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }
    }

    public Guard(final char[][] grid, final Point startLocation) {
        this.grid = grid;
        this.visitedLocations = new ArrayList<>();
        this.visitedLocationsByDirection = new EnumMap<>(Direction.class);
        this.currentLocation = startLocation;
        this.facingDirection = Direction.NORTH;
        moveUntilLeavingMap();
    }

    public void moveUntilLeavingMap() {
        while (isLocationInsideMap(this.currentLocation)) {
            this.visitedLocations.add(currentLocation);
            if (!this.visitedLocationsByDirection.computeIfAbsent(this.facingDirection, collection -> new HashSet<>())
                    .add(currentLocation)) {
                throw new IllegalStateException("Loop detected!");
            }
            this.currentLocation = move();
        }
    }

    private boolean isLocationInsideMap(final Point location) {
        return location.x >= 0 && location.x < this.grid.length
                && location.y >= 0 && location.y < this.grid[0].length;
    }

    public Point move() {
        Point result;
        while (isLocationInsideMap(result = getNextLocation()) && isLocationObstructive(result)) {
            this.facingDirection = facingDirection.getRight();
        }
        return result;
    }

    private boolean isLocationObstructive(final Point location) {
        return grid[location.y][location.x] == '#';
    }

    private Point getNextLocation() {
        final Point result = new Point(currentLocation);
        result.translate(facingDirection.getMoveX(), facingDirection.getMoveY());
        return result;
    }

    public long countDistinctPositionsVisited() {
        return new HashSet<>(visitedLocations).size();
    }
}
