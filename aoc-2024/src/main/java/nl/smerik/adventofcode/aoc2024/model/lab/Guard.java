package nl.smerik.adventofcode.aoc2024.model.lab;

import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Guard {

    private final char[][] grid;
    private final List<Point> visitedLocations;
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

    public Guard(final List<String> lines) {
        this.grid = parseLines(lines);
        this.visitedLocations = new ArrayList<>();
        this.currentLocation = findCurrentPosition();
        this.facingDirection = Direction.NORTH;
        moveUntilLeavingMap();
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            result[row] = lines.get(row).toCharArray();
        }
        return result;
    }

    private Point findCurrentPosition() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '^') {
                    return new Point(col, row);
                }
            }
        }
        throw new IllegalStateException("Guard not found!");
    }

    public void moveUntilLeavingMap() {
        while (isLocationInsideMap(this.currentLocation)) {
            this.visitedLocations.add(currentLocation);
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
