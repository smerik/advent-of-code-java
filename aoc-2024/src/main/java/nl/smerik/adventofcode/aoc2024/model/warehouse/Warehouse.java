package nl.smerik.adventofcode.aoc2024.model.warehouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Warehouse {

    private final boolean scaleUp;
    private final CellType[][] grid;
    private final List<Direction> directions;

    public Warehouse(final List<String> lines, final boolean scaleUp) {
        final Iterator<String> iterator = lines.iterator();
        this.scaleUp = scaleUp;
        this.grid = parseGrid(iterator);
        this.directions = parseDirections(iterator);
    }

    private CellType[][] parseGrid(final Iterator<String> iterator) {
        final List<String> rows = new ArrayList<>();
        String line;
        while (iterator.hasNext() && !(line = iterator.next()).isBlank()) {
            if (scaleUp) {
                line = scaleUpLine(line);
            }
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

    private String scaleUpLine(final String line) {
        final StringBuilder sb = new StringBuilder(2 * line.length());
        for (final char token : line.toCharArray()) {
            switch (CellType.valueOfToken(token)) {
                case BOX:
                    sb.append(CellType.BOX_LEFT).append(CellType.BOX_RIGHT);
                    break;
                case ROBOT:
                    sb.append(CellType.ROBOT).append(CellType.EMPTY);
                    break;
                default:
                    sb.append(token).append(token);
            }
        }
        return sb.toString();
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
            if (isAllowedToMove(robotLocation, direction)) {
                move(robotLocation, direction);
            }
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

    private boolean isAllowedToMove(final Point location, final Direction direction) {
        final Point newLocation = getNewLocation(location, direction);
        if (isOutOfBounds(newLocation)) {
            return false;
        }

        final CellType newLocationType = grid[newLocation.y][newLocation.x];
        boolean allowed = switch (newLocationType) {
            case BOX, BOX_LEFT, BOX_RIGHT -> isAllowedToMove(newLocation, direction);
            case EMPTY -> true;
            default -> false;
        };
        if (allowed && direction.movesVertically()) {
            if (newLocationType == CellType.BOX_LEFT) {
                final Point boxRightLocation = new Point(newLocation.x + 1, newLocation.y);
                allowed = isAllowedToMove(boxRightLocation, direction);
            } else if (newLocationType == CellType.BOX_RIGHT) {
                final Point boxLeftLocation = new Point(newLocation.x - 1, newLocation.y);
                allowed = isAllowedToMove(boxLeftLocation, direction);
            }
        }
        return allowed;
    }

    private void move(final Point location, final Direction direction) {
        final Point newLocation = getNewLocation(location, direction);
        final CellType newLocationType = grid[newLocation.y][newLocation.x];
        if (newLocationType.isBox()) {
            move(newLocation, direction);
            if (direction.movesVertically()) {
                if (newLocationType == CellType.BOX_LEFT) {
                    final Point boxRightLocation = new Point(newLocation.x + 1, newLocation.y);
                    move(boxRightLocation, direction);
                } else if (newLocationType == CellType.BOX_RIGHT) {
                    final Point boxLeftLocation = new Point(newLocation.x - 1, newLocation.y);
                    move(boxLeftLocation, direction);
                }
            }
        }
        moveAndClear(location, newLocation);
    }


    private Point getNewLocation(Point location, Direction direction) {
        final Point result = new Point(location);
        result.translate(direction.getDistanceX(), direction.getDistanceY());
        return result;
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
        final CellType boxType = scaleUp ? CellType.BOX_LEFT : CellType.BOX;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == boxType) {
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
