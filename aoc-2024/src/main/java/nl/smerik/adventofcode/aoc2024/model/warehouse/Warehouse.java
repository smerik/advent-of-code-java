package nl.smerik.adventofcode.aoc2024.model.warehouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Warehouse {

    private final CellType[][] grid;
    private final List<Direction> directions;

    public Warehouse(final List<String> lines) {
        final Iterator<String> iterator = lines.iterator();
        this.grid = parseGrid(iterator);
        this.directions = parseDirections(iterator);
    }

    private CellType[][] parseGrid(final Iterator<String> iterator) {
        final List<String> rows = new ArrayList<>();
        String line;
        while (iterator.hasNext() && !(line = iterator.next()).isBlank()) {
            rows.add(line);
        }
        final CellType[][] result = new CellType[rows.size()][rows.get(0).length()];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                result[row][col] = CellType.valueOfToken(rows.get(row).charAt(col));
            }
        }
        return result;
    }

    private List<Direction> parseDirections(final Iterator<String> iterator) {
        final List<Direction> result = new ArrayList<>();
        while (iterator.hasNext()) {
            for (final char token : iterator.next().toCharArray()) {
                result.add(Direction.valueOfToken(token));
            }
        }
        return result;
    }

    public void moveRobot() {
        directions.forEach(direction -> {
            final Point robotLocation = findRobotLocation();
            move(robotLocation, direction);
        });
    }

    private Point findRobotLocation() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == CellType.ROBOT) {
                    return new Point(col, row);
                }
            }
        }
        throw new IllegalStateException("No robot found!");
    }

    private boolean move(final Point location, final Direction direction) {
        final Point newLocation = new Point(location);
        newLocation.translate(direction.getDistanceX(), direction.getDistanceY());
        if (isOutOfBounds(newLocation)) {
            return false;
        }

        final CellType newLocationType = grid[newLocation.y][newLocation.x];
        if (newLocationType == CellType.EMPTY
                || newLocationType == CellType.BOX && move(newLocation, direction)) {
            moveAndClear(location, newLocation);
            return true;
        }
        return false;
    }

    private void moveAndClear(final Point fromLocation, final Point toLocation) {
        grid[toLocation.y][toLocation.x] = grid[fromLocation.y][fromLocation.x];
        grid[fromLocation.y][fromLocation.x] = CellType.EMPTY;
    }

    private boolean isOutOfBounds(final Point point) {
        return point.y < 0 || point.y >= grid.length || point.x < 0 || point.x >= grid[0].length;
    }

    public int sumGPSCoordinates() {
        int result = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == CellType.BOX) {
                    result += 100 * row + col;
                }
            }
        }
        return result;
    }

    public String render() {
        final StringBuilder sb = new StringBuilder();
        for (final CellType[] row : grid) {
            for (int col = 0; col < grid[0].length; col++) {
                sb.append(row[col]);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
